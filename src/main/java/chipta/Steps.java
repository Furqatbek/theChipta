package chipta;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public interface Steps {
    String START = "START";
    String CHOOSE_TRAIN_FROM = "CHOOSE_TRAIN_FROM";
    String CHOOSE_TRAIN_TO = "CHOOSE_TRAIN_TO";
    String SETTINGS = "SETTINGS";
    String REGISTRATION = "REGISTRATION";
}
