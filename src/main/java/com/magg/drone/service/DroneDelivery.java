package com.magg.drone.service;

import com.magg.drone.model.Drone;
import com.magg.drone.service.impl.RouteProcessorImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;

import static com.magg.drone.config.DroneDeliveryConfig.MAX_THREADS;
import static com.magg.drone.utils.Constants.FILE_SUFFFIX;
import static com.magg.drone.utils.Constants.INPUT_FILE_PREFIX;
import static com.magg.drone.utils.Constants.OUTPUT_FILE_PREFIX;
import static com.magg.drone.utils.FileUtils.createFile;
import static com.magg.drone.utils.FileUtils.readFile;

/**
 * DroneDelivery class, it is business logic driver and processes all routes for all given drones.
 */
@Slf4j
public class DroneDelivery
{

    private final ExecutorService executorService;
    private final RouteProcessor routeProcessor;
    private final String FILE_PATH;

    public DroneDelivery(String filePath) {
        this.executorService = Executors.newFixedThreadPool(MAX_THREADS);
        this.routeProcessor = new RouteProcessorImpl();
        this.FILE_PATH = filePath;
    }


    /**
     * Main method to start the delivery process using multi threading.
     */
    public void makeDeliveries() {

        List<CompletableFuture<Void>> futures = new ArrayList<>();
        try {
            for(int i = 1; i <= MAX_THREADS; i++) {
                int finalI = i;
                futures.add(CompletableFuture.runAsync(() -> processSingleDelivery(finalI), executorService));
            }
            futures.forEach(CompletableFuture::join);
        } catch(Exception e) {
            log.error("Thread processing error", e);
        } finally {
            executorService.shutdownNow();
        }
    }


    /**
     * Reads input file, processes the routes, and creates the output file.
     *
     * @param id the drone id
     * @return the list of processed routes
     */
    public List<String> processSingleDelivery(int id) {
        String formatted = String.format("%s%s%02d%s", FILE_PATH, INPUT_FILE_PREFIX, id, FILE_SUFFFIX);
        List<String> lines = readFile(formatted);
        List<String> out = routeProcessor.process(lines, new Drone());
        String formattedOut = String.format("%s%s%02d%s", FILE_PATH, OUTPUT_FILE_PREFIX, id, FILE_SUFFFIX);
        createFile(out, formattedOut);
        return out;
    }
}
