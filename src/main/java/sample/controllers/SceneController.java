package sample.controllers;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import sample.classes.team.Team;

import java.net.URL;
import java.util.*;

public class SceneController implements Initializable {
    // TODO? put baskets and groups into an array, for later letting the user to choose
    // the number of baskets and groups and so that they can be iterated on?
    @FXML private ListView<String> basket1;
    @FXML private ListView<String> basket2;
    @FXML private ListView<String> basket3;
    @FXML private ListView<String> basket4;

    @FXML private Button drawButton;
    @FXML private Button quickDrawButton;
    @FXML private Button resetButton;

    @FXML private ListView<String> groupA;
    @FXML private ListView<String> groupB;
    @FXML private ListView<String> groupC;
    @FXML private ListView<String> groupD;
    @FXML private ListView<String> groupE;
    @FXML private ListView<String> groupF;
    @FXML private ListView<String> groupG;
    @FXML private ListView<String> groupH;

    private Map<String, Team> teams;
    private Team host;

    // TODO check if it's okay to instantiate this way
    private ObservableList<String>[] observableListBasket = new ObservableList[4]; // names of teams in each basket
    private ObservableList<String>[] observableListGroup = new ObservableList[8]; // array with names of teams in each

    // TODO should be more universal
    public static final int NUMBER_OF_BASKETS = 4; // the amount of baskets
    public static final int TEAMS_IN_BASKET = 8; // the amount of teams in a single basket

    // styles for different button states
    private static final String BUTTON_DISABLED_STYLE = "-fx-base: #444444; -fx-text-fill: #333333;";
    private static final String BUTTON_ENABLED_STYLE = "-fx-base: #000000; -fx-text-fill: #FFFFFF;";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeFields();
        setDefaultTeams();
    }

    @FXML
    protected void handleDrawButton() {
    }

    @FXML
    protected void handleQuickDrawButton() throws InterruptedException {
        int nextTeam = 8;
        int rand;
        String nameDrawn;

        // instantiate all ObservableLists for team names in groups
        for (int i = 0; i < TEAMS_IN_BASKET; i++) {
            observableListGroup[i] = FXCollections.observableArrayList();
        }

        // binding ObservableList with names of teams and ListView
        groupA.setItems(observableListGroup[0]);
        groupB.setItems(observableListGroup[1]);
        groupC.setItems(observableListGroup[2]);
        groupD.setItems(observableListGroup[3]);
        groupE.setItems(observableListGroup[4]);
        groupF.setItems(observableListGroup[5]);
        groupG.setItems(observableListGroup[6]);
        groupH.setItems(observableListGroup[7]);

        setDisableDrawButtons(true);
        setButtonsStyle(BUTTON_DISABLED_STYLE, drawButton, quickDrawButton);
    }

    @FXML
    protected void handleResetButton() {
        setDefaultTeams();
        cleanLists();
        setDisableDrawButtons(false);
        setButtonsStyle(BUTTON_ENABLED_STYLE, drawButton, quickDrawButton);
    }

    private void initializeFields() {
        teams = new HashMap<>();
    }

    Ordering<Map.Entry<String, Team>> byMapValues = new Ordering<Map.Entry<String, Team>>() {
        @Override
        public int compare(Map.Entry<String, Team> left, Map.Entry<String, Team> right) {
            return left.getValue().compareTo(right.getValue());
        }
    };

    public void sortTeamsMapByValuesDescending() {
        // create a list of map entries
        List<Map.Entry<String, Team>> _teams = Lists.newArrayList(teams.entrySet());
        Collections.sort(_teams, byMapValues.reverse());
    }

    private void setHost(Team team) {
        host = team;
        teams.put(team.getName(), team);
    }

    private Team getHost() {
        return host;
    }

    @Deprecated
    private void setDefaultTeams() {
        observableListBasket[0]
                = FXCollections.observableArrayList("Rosja", "Niemcy",
                "Brazylia", "Portugalia",
                "Argentyna", "Belgia",
                "Polska", "Francja");
        basket1.setItems(observableListBasket[0]);

        observableListBasket[1]
                = FXCollections.observableArrayList("Hiszpania", "Peru",
                "Szwajcaria", "Anglia",
                "Kolumbia", "Meksyk",
                "Urugwaj", "Chorwacja");
        basket2.setItems(observableListBasket[1]);

        observableListBasket[2]
                = FXCollections.observableArrayList("Dania", "Islandia",
                "Kostaryka", "Szwecja",
                "Tunezja", "Egipt",
                "Senegal", "Iran");
        basket3.setItems(observableListBasket[2]);

        observableListBasket[3]
                = FXCollections.observableArrayList("Serbia", "Nigeria",
                "Australia", "Japonia",
                "Maroko", "Panama",
                "Korea Południowa", "Arabia Saudyjska");
        basket4.setItems(observableListBasket[3]);
    }

    /**
     * @see #setButtonStyle(Button, String)
     * @see #setButtonsStyle(String, Button...)
     */
    @Deprecated
    private void setDisabledStyleAndOnAction(Button button) {
        button.setOnAction(null);
        quickDrawButton.setStyle(BUTTON_DISABLED_STYLE);
    }

    /**
     * Sets style of a button. Available styles:
     * <br>{@linkplain #BUTTON_ENABLED_STYLE}
     * <br>{@linkplain #BUTTON_DISABLED_STYLE}
     * @param button button to apply the style to
     * @param STYLE the style to apply to the button
     */
    private void setButtonStyle(Button button, String STYLE) {
        quickDrawButton.setStyle(STYLE);
    }

    /**
     * Sets the style of buttons array. Available styles:
     * <br>{@linkplain #BUTTON_ENABLED_STYLE}
     * <br>{@linkplain #BUTTON_DISABLED_STYLE}
     * @param STYLE the style to apply to all buttons
     * @param buttons buttons to apply the style to
     */
    private void setButtonsStyle(String STYLE, Button... buttons) {
        for (Button x : buttons) {
            x.setStyle(STYLE);
        }
    }

    /**
     * Sets the {@link SceneController#drawButton} and {@link SceneController#quickDrawButton}
     * to the desired state.
     * @param state the state to set the buttons
     */
    private void setDisableDrawButtons(boolean state) {
        drawButton.setDisable(state);
        quickDrawButton.setDisable(state);
    }

    /**
     * Instantiaties and initializes all teams
     */
    private void makeTeams() {
    }

    private void cleanLists() {
        for (int i = 0; i < TEAMS_IN_BASKET; i++) {
            observableListGroup[i].clear();
        }
    }
}
