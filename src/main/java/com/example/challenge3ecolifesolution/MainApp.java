package com.example.challenge3ecolifesolution;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class MainApp extends Application {

    private int tipIndex = 0;
    private final String[] gardeningTips = {
            "🌱 Tip: Water your plants in the early morning.",
            "🍃 Tip: Use organic compost for healthier soil.",
            "☀️ Tip: Ensure your plants get 6 hours of sunlight.",
            "🌼 Tip: Mulch your garden to retain moisture.",
            "🐛 Tip: Check leaves regularly for pests."
    };

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(20);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(25));
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #f1f8e9, #dcedc8);");


        Label titleLabel = new Label("EcoLife Solution");
        titleLabel.setStyle("-fx-font-size: 32px; -fx-font-weight: bold; -fx-text-fill: #1b5e20;");
        VBox.setMargin(titleLabel, new Insets(65, 0, 0, 0));


        Label tipLabel = new Label(gardeningTips[0]);
        tipLabel.setStyle("-fx-text-fill: #2e7d32; -fx-font-weight: bold; -fx-font-size: 16px;");


        TranslateTransition tipMove = new TranslateTransition(Duration.seconds(2), tipLabel);
        tipMove.setByX(30);
        tipMove.setCycleCount(TranslateTransition.INDEFINITE);
        tipMove.setAutoReverse(true);
        tipMove.play();


        Timeline tipCycle = new Timeline(new KeyFrame(Duration.seconds(5), e -> {
            tipIndex = (tipIndex + 1) % gardeningTips.length;
            tipLabel.setText(gardeningTips[tipIndex]);
        }));
        tipCycle.setCycleCount(Timeline.INDEFINITE);
        tipCycle.play();


        TextField countryField = new TextField();
        countryField.setPromptText("Enter Country Name...");
        countryField.setPrefWidth(280);
        countryField.setStyle("-fx-background-radius: 20; -fx-border-radius: 20; -fx-border-color: #a5d6a7; -fx-border-width: 2; -fx-padding: 8;");

        Button searchBtn = new Button("Search");
        searchBtn.setStyle("-fx-background-color: #2e7d32; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 20; -fx-padding: 8 25;");
        searchBtn.disableProperty().bind(countryField.textProperty().isEmpty());

        HBox searchBox = new HBox(10, countryField, searchBtn);
        searchBox.setAlignment(Pos.CENTER);


        SVGPath flowerIcon = new SVGPath();
        flowerIcon.setContent("M20 10 Q25 0 30 10 Q40 10 30 20 Q30 30 20 20 Q10 30 10 20 Q0 10 10 10 Q10 0 20 10");
        flowerIcon.setFill(Color.web("#2e7d32"));
        flowerIcon.setScaleX(2.8);
        flowerIcon.setScaleY(2.8);

        TranslateTransition flowerMove = new TranslateTransition(Duration.seconds(2), flowerIcon);
        flowerMove.setByX(45);
        flowerMove.setCycleCount(TranslateTransition.INDEFINITE);
        flowerMove.setAutoReverse(true);
        flowerMove.play();

        Label locationLabel = new Label("Location: Waiting...");
        locationLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #2e7d32;");

        Label tempLabel = new Label("--°C");
        tempLabel.setStyle("-fx-font-size: 60px; -fx-font-weight: bold; -fx-text-fill: #212121;");

        Label airQualityLabel = new Label("Air Quality Index: --");
        Label soilAdviceLabel = new Label("Good for soil prep!");
        soilAdviceLabel.setStyle("-fx-font-style: italic; -fx-text-fill: #1b5e20; -fx-font-size: 15px;");
        soilAdviceLabel.setVisible(false);


        ListView<String> logList = new ListView<>();
        logList.setPrefHeight(350);
        logList.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #c8e6c9;");

        ScrollPane scrollLog = new ScrollPane(logList);
        scrollLog.setFitToWidth(true);
        VBox.setMargin(scrollLog, new Insets(-5, 0, 0, 0));

        root.getChildren().addAll(titleLabel, tipLabel, searchBox, locationLabel, flowerIcon, tempLabel, airQualityLabel, soilAdviceLabel, scrollLog);


        Random random = new Random();
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("E");

        searchBtn.setOnAction(e -> {
            String country = countryField.getText();
            LocalDateTime now = LocalDateTime.now();

            String currentDay = dayFormatter.format(now);
            String dateInfo = String.format("Dated: %02d Monthly: %02d", now.getDayOfMonth(), now.getMonthValue());
            String timeInfo = String.format("%02d:%02d:%02d", now.getHour(), now.getMinute(), now.getSecond());

            locationLabel.setText("Country: " + country);
            soilAdviceLabel.setVisible(true);

            int deg = 18 + random.nextInt(12);
            tempLabel.setText(deg + "°C");

            int aqi = 30 + random.nextInt(40);
            airQualityLabel.setText("Air Quality Index: " + aqi + " (Verified)");

            logList.getItems().add(0, String.format("[%s] %s | %s | %s | %d°C | Good for soil prep!",
                    currentDay, dateInfo, timeInfo, country, deg));
        });

        Scene scene = new Scene(root, 650, 950);
        stage.setTitle("EcoLife Solution - Gardening Tech");
        stage.setScene(scene);
        stage.show();
    }
}