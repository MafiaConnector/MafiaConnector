package com.ksk.mf.gui;

import com.ksk.mf.AuthUtil;
import com.ksk.mf.channel.MafiaChannel;
import com.ksk.mf.game.info.MafiaLoginInfo;
import com.ksk.mf.packet.PacketId;
import com.ksk.mf.packet.request.RequestPacket;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.ResourceBundle;

public class MainController implements Initializable, ConnectorCallback {
    private static MainController instance;

    @FXML private ComboBox<MafiaChannel> channelComboBox;
    @FXML private TextField deviceKeyField;
    @FXML private TextField authTokenField;
    @FXML private Button connectButton;
    @FXML private Button disconnectButton;
    @FXML private Button loadDeviceKeyButton;
    @FXML private Button loadAuthTokenButton;
    @FXML private Label connectionStatusLabel;
    @FXML private TextArea logTextArea;

    // User search fields
    @FXML private TextField searchUserField;
    @FXML private Button searchUserButton;
    @FXML private Button clearUserButton;

    @FXML private Label userNameLabel;
    @FXML private Label userIdLabel;
    @FXML private Label loverLabel;
    @FXML private Label loverDateCountLabel;
    @FXML private Label newFriendChatLabel;
    @FXML private Label fameLabel;
    @FXML private Label onlineLabel;
    @FXML private Label lastLoginLabel;
    @FXML private Label introduceLabel;
    @FXML private Label guildNameLabel;
    @FXML private Label guildLevelLabel;
    @FXML private Label guildPointLabel;

    private GuiConnector guiConnector;
    private Thread connectorThread;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this; // Singleton 인스턴스 설정
        setupChannelComboBox();
        setupInitialState();
        appendLog("Application started.");
    }

    // 다른 클래스에서 MainController 인스턴스에 접근하는 메서드
    public static MainController getInstance() {
        return instance;
    }

    private void setupChannelComboBox() {
        channelComboBox.setItems(FXCollections.observableList(
            Arrays.asList(MafiaChannel.values())
        ));
        channelComboBox.getSelectionModel().select(MafiaChannel.CHANNEL_RANK);
    }

    private void setupInitialState() {
        // Fields are initially empty, values will be loaded from .env via AuthUtil when needed
        deviceKeyField.setText("");
        authTokenField.setText("");
        updateConnectionStatus(false);
        appendLog("Ready to connect. Device Key and Auth Token will be loaded from .env file automatically.");
    }

    @FXML
    private void handleConnect() {
        String deviceKey = deviceKeyField.getText().trim();
        String authToken = authTokenField.getText().trim();
        MafiaChannel selectedChannel = channelComboBox.getSelectionModel().getSelectedItem();

        // If fields are empty, get from AuthUtil (.env file)
        if (deviceKey.isEmpty()) {
            try {
                deviceKey = AuthUtil.instance.getDeviceKey();
                if (!deviceKey.isEmpty() && !deviceKey.equals("null")) {
                    appendLog("Device Key loaded from .env file");
                } else {
                    throw new Exception("Device Key not found");
                }
            } catch (Exception e) {
                showAlert("Error", "Device Key not found in .env file! Please check DEVICE_KEY environment variable.");
                return;
            }
        }

        if (authToken.isEmpty()) {
            try {
                authToken = AuthUtil.instance.getAuthToken();
                if (!authToken.isEmpty() && !authToken.equals("null")) {
                    appendLog("Auth Token loaded from .env file");
                } else {
                    throw new Exception("Auth Token not found");
                }
            } catch (Exception e) {
                showAlert("Error", "Auth Token not found in .env file! Please check ACCESS_TOKEN, MAFIA_CONFIG_FILE, and AUTH_FILE_GOOGLE environment variables.");
                return;
            }
        }

        // Final validation
        if (deviceKey.equals("null")) {
            showAlert("Error", "Device Key is required! Please check .env file configuration.");
            return;
        }

        if (authToken.equals("null")) {
            showAlert("Error", "Auth Token is required! Please check .env file configuration.");
            return;
        }

        if (selectedChannel == null) {
            showAlert("Error", "Please select a channel!");
            return;
        }

        System.setProperty("deviceKey", deviceKey);
        System.setProperty("mafiaAuth", authToken);

        appendLog("Connecting to " + selectedChannel.name + "...");

        guiConnector = new GuiConnector(selectedChannel, this);
        connectorThread = new Thread(guiConnector);
        connectorThread.setDaemon(true);
        connectorThread.start();
    }

    @FXML
    private void handleDisconnect() {
        if (guiConnector != null) {
            appendLog("Disconnecting...");
            guiConnector.disconnect();
            if (connectorThread != null) {
                connectorThread.interrupt();
            }
        }
    }

    @FXML
    private void handleExit() {
        if (guiConnector != null) {
            handleDisconnect();
        }
        Platform.exit();
    }

    @FXML
    private void handleAbout() {
        showAlert("About", "Mafia Connector GUI\nVersion 1.0\nBuilt with JavaFX");
    }

    @FXML
    private void handleLoadDeviceKey() {
        try {
            String deviceKey = AuthUtil.instance.getDeviceKey();
            if (!deviceKey.isEmpty() && !deviceKey.equals("null")) {
                // Show masked version for security
                String masked = deviceKey.substring(0, Math.min(8, deviceKey.length())) + "****";
                deviceKeyField.setText(masked);
                appendLog("Device Key loaded from .env file (masked for security)");
            } else {
                showAlert("Warning", "Device Key not found in .env file. Please check DEVICE_KEY environment variable.");
                appendLog("Failed to load Device Key from .env file");
            }
        } catch (Exception e) {
            showAlert("Error", "Error loading Device Key: " + e.getMessage());
            appendLog("Error loading Device Key: " + e.getMessage());
        }
    }

    @FXML
    private void handleLoadAuthToken() {
        try {
            String authToken = AuthUtil.instance.getAuthToken();
            if (!authToken.isEmpty() && !authToken.equals("null")) {
                // Show masked version for security
                authTokenField.setText("****" + authToken.substring(Math.max(0, authToken.length() - 4)));
                appendLog("Auth Token loaded from .env file (masked for security)");
            } else {
                showAlert("Warning", "Auth Token not found in .env file. Please check ACCESS_TOKEN, MAFIA_CONFIG_FILE, and AUTH_FILE_GOOGLE environment variables.");
                appendLog("Failed to load Auth Token from .env file");
            }
        } catch (Exception e) {
            showAlert("Error", "Error loading Auth Token: " + e.getMessage());
            appendLog("Error loading Auth Token: " + e.getMessage());
        }
    }

    @FXML
    private void handleSearchUser() {
        String username = searchUserField.getText().trim();

        if (username.isEmpty()) {
            showAlert("Warning", "Please enter a username to search.");
            return;
        }

        if (!GuiConnector.isConnected()) {
            showAlert("Error", "Not connected to server. Please connect first.");
            return;
        }

        try {
            // Create and send search username request packet
            RequestPacket searchPacket = new RequestPacket(PacketId.REQUEST_SEARCH_USERNAME, username);
            GuiConnector.sendPacket(searchPacket);

            appendLog("Searching for user: " + username);
            appendLog("Search request sent. Waiting for response...");

        } catch (Exception e) {
            appendLog("Error searching user: " + e.getMessage());
            showAlert("Error", "Failed to search user: " + e.getMessage());
        }
    }

    @FXML
    private void handleClearUser() {
        // Clear search field
        searchUserField.clear();

        // Clear user info display
        clearUserInfo();

        appendLog("User information cleared.");
    }

    private void updateConnectionStatus(boolean connected) {
        connectButton.setDisable(connected);
        disconnectButton.setDisable(!connected);
        channelComboBox.setDisable(connected);
        deviceKeyField.setDisable(connected);
        authTokenField.setDisable(connected);

        connectionStatusLabel.setText(connected ? "Connected" : "Disconnected");
        connectionStatusLabel.setStyle(connected ? "-fx-text-fill: green;" : "-fx-text-fill: red;");
    }

    private void appendLog(String message) {
        Platform.runLater(() -> {
            String timestamp = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            );
            logTextArea.appendText("[" + timestamp + "] " + message + "\n");
        });
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void updateUserInfo(MafiaLoginInfo loginInfo) {
        Platform.runLater(() -> {
            if (loginInfo != null) {
                userNameLabel.setText(loginInfo.getUserName() != null ? loginInfo.getUserName() : "-");
                userIdLabel.setText(String.valueOf(loginInfo.getId()));
                loverLabel.setText(String.valueOf(loginInfo.getCoupleNickname()));
                loverDateCountLabel.setText(String.valueOf(loginInfo.getCoupleDateCount()));
                newFriendChatLabel.setText(String.valueOf(loginInfo.getNewFriendChat()));
                fameLabel.setText(String.valueOf(loginInfo.getFame()));
                onlineLabel.setText(loginInfo.isOnline() ? "Yes" : "No");

                if (loginInfo.getLastLoginTime() != null) {
                    lastLoginLabel.setText(loginInfo.getLastLoginTime().format(
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    ));
                }

                introduceLabel.setText(loginInfo.getIntroduce() != null ? loginInfo.getIntroduce() : "-");

                guildNameLabel.setText(loginInfo.getGuildName() != null ? loginInfo.getGuildName() : "-");
                guildLevelLabel.setText(String.valueOf(loginInfo.getGuildLevel()));
                guildPointLabel.setText(String.valueOf(loginInfo.getGuildPoint()));

                appendLog("User info updated: " + loginInfo.getUserName());
            }
        });
    }

    private void clearUserInfo() {
        userNameLabel.setText("-");
        userIdLabel.setText("-");
        loverLabel.setText("-");
        loverDateCountLabel.setText("-");
        newFriendChatLabel.setText("-");
        fameLabel.setText("-");
        onlineLabel.setText("-");
        lastLoginLabel.setText("-");
        introduceLabel.setText("-");
        guildNameLabel.setText("-");
        guildLevelLabel.setText("-");
        guildPointLabel.setText("-");
    }

    // ConnectorCallback implementations
    @Override
    public void onConnected() {
        Platform.runLater(() -> {
            updateConnectionStatus(true);
            appendLog("Connected successfully!");
        });
    }

    @Override
    public void onDisconnected() {
        Platform.runLater(() -> {
            updateConnectionStatus(false);
            clearUserInfo();
            appendLog("Disconnected.");
        });
    }

    @Override
    public void onError(String error) {
        Platform.runLater(() -> {
            updateConnectionStatus(false);
            appendLog("Error: " + error);
        });
    }

    @Override
    public void onLoginInfoReceived(MafiaLoginInfo loginInfo) {
        updateUserInfo(loginInfo);
    }

    @Override
    public void onMessageReceived(String message) {
        appendLog("Received: " + message);
    }
}