package chipta;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Chipta extends TelegramLongPollingBot {
    private static final String botToken = "5440974161:AAE5rkmtBTCKU9vY6KWvL2PAGlbVED1t-Fc";
    private static final String botUserName = "thechiptabot";

    //controller of steps
    String STEP = "START";

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Scarping scarping = new Scarping();
        SendMessage message = new SendMessage();
        Lines lines = new Lines();
        String message_text = update.getMessage().getText();
        long chat_id = update.getMessage().getChatId();
        StringBuilder fromWhere = new StringBuilder();
        StringBuilder toWhere = new StringBuilder();

        //staring bot
        if (update.hasMessage() && update.getMessage().hasText()) {
            
            //on /start
            if (message_text.equals("/start")) {
                message.setChatId(chat_id);
                message.setText("Assalomu aleykum " + update.getMessage().getFrom().getFirstName() + System.lineSeparator() + "Asosiy menu.");

                // Create ReplyKeyboardMarkup object
                ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
                keyboardMarkup.setResizeKeyboard(true);
                // Create the keyboard (list of keyboard rows)
                List<KeyboardRow> keyboard = new ArrayList<>();

                // Create a keyboard row
                KeyboardRow row = new KeyboardRow();

                // Set each button, you can also use KeyboardButton objects if you need something else than text
                row.add("Chipta izlash. 🔎");
                row.add("Sozlamalar. ⚙️");

                // Add the first row to the keyboard
                keyboard.add(row);

                // Set the keyboard to the markup
                keyboardMarkup.setKeyboard(keyboard);

                // Add it to the message
                message.setReplyMarkup(keyboardMarkup);

                STEP = Steps.START;
            }

            //on click search ticket
            if (message_text.equals("Chipta izlash. 🔎")) {
                message.setChatId(chat_id);
                message.setText("Yo'nalishni kiriting. Qayerdan?\n" + lines.lines());
                STEP = Steps.CHOOSE_TRAIN_FROM;
            }

            //choose where to go from
            if (lines.correctLineFrom(message_text) && STEP.equals("CHOOSE_TRAIN_FROM")) {
                fromWhere.append(message_text);
                message.setChatId(chat_id);
                message.setText("Jo'nab ketish manzili: " + fromWhere.toString().toUpperCase(Locale.ROOT) + System.lineSeparator() + "Borish manzilini kiriting.");
                STEP = Steps.CHOOSE_TRAIN_TO;
            }

            //choose where to go to
            if (lines.correctLineFrom(message_text) && STEP.equals("CHOOSE_TRAIN_TO")) {
                toWhere.append(message_text);
                message.setChatId(chat_id);
                message.setText("Borish manzilingiz: " + toWhere.toString().toUpperCase());
            }

            //on click settings
            if (message_text.equals("Sozlamalar. ⚙️")) {
                message.setChatId(chat_id);
                message.setText("Malumot topilmadi.");
                STEP = Steps.SETTINGS;
            }

        }

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


}





