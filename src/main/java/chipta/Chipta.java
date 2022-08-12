package chipta;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Chipta extends TelegramLongPollingBot {
    private static final String botToken = "5440974161:AAE5rkmtBTCKU9vY6KWvL2PAGlbVED1t-Fc";
    private static final String botUserName = "thechiptabot";

    //get username
    private StringBuilder userName = new StringBuilder();

    //get user phone number
    private StringBuilder phoneNumber = new StringBuilder();

    //controller of steps
    String STEP = "START";

    @Override
    public void onUpdateReceived(Update update) {

        SendMessage message = new SendMessage();
        Lines lines = new Lines();
        KeyboardButton keyboardButton = new KeyboardButton();
        Message msg = new Message();
        Contact contact = new Contact();

        //user message
        String message_text = update.getMessage().getText();

        //user id
        long chat_id = update.getMessage().getChatId();

        //user's A and B
        StringBuilder fromWhere = new StringBuilder();
        StringBuilder toWhere = new StringBuilder();

        //staring bot
        if (update.hasMessage()) {

            //on /start
            if (message_text.equals("/start")) {
                message.setChatId(chat_id);
                message.setText("Assalomu aleykum " + update.getMessage().getFrom().getFirstName() + System.lineSeparator() + "Botdan foydalanish uchun iltimos ro'yhatdan o'ting.\n" + "Telefon raqamingizni yuboring");

                // create keyboard
                ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                message.setReplyMarkup(replyKeyboardMarkup);
                replyKeyboardMarkup.setSelective(true);
                replyKeyboardMarkup.setResizeKeyboard(true);
                replyKeyboardMarkup.setOneTimeKeyboard(true);

                // new list
                List<KeyboardRow> keyboard = new ArrayList<>();

                // first keyboard line
                KeyboardRow keyboardFirstRow = new KeyboardRow();
                keyboardButton.setText("Raqamni yuborish 📞");
                keyboardButton.setRequestContact(true);
                keyboardFirstRow.add(keyboardButton);

                // add array to list
                keyboard.add(keyboardFirstRow);

                // add list to our keyboard
                replyKeyboardMarkup.setKeyboard(keyboard);

                if (update.hasMessage()) {
                    System.out.println(update.getMessage().getContact());
                }

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


    public StringBuilder getUserName() {
        return userName;
    }

    public void setUserName(StringBuilder userName) {
        this.userName = userName;
    }

    public StringBuilder getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(StringBuilder phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}





