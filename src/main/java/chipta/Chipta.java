package chipta;

import org.jsoup.Connection;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

public class Chipta extends TelegramLongPollingBot {
    // fromWhere
    StringBuilder fromWhere = new StringBuilder();
    //toWhere
    StringBuilder toWhere = new StringBuilder();

    //when
    StringBuilder when = new StringBuilder();

    String[] cities = new String[]{"nukus", "buxoro", "urganch", "hazarasp", "samarqand", "toshkent", "navoiy", "andijon", "qarshi", "jizzax", "termiz", "xiva", "guliston"};

    // searching correct line
    public boolean correctLine(String cityName) {
        for (String city : cities) {
            if (cityName.toLowerCase().equals(city)) {
                return true;
            }
        }
        return false;
    }

    public void connect() {
        Connection.Response response = null;
        try {
            response = Jsoup.connect("https://chipta.railway.uz/ru/home")
                    .data("search--trains__input--from", fromWhere.toString(), "search--trains__input--to", toWhere.toString())
                    .method(Connection.Method.POST)
                    .execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(response);

    }


    @Override
    public String getBotUsername() {
        return "thechiptabot";
    }

    @Override
    public String getBotToken() {
        return "5440974161:AAE5rkmtBTCKU9vY6KWvL2PAGlbVED1t-Fc";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage();
        String text = message.getText();
        connect();
        //staring bot
        if (update.hasMessage() && message.hasText() && text.equals("/start")) {
            sendMessage.setText("Manzilingizni kiriting...");
            sendMessage.setChatId(message.getChatId());
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }

        //choosing line fromWhere
        if (update.hasMessage() && correctLine(text)) {
            fromWhere = new StringBuilder(message.getText());
            sendMessage.setText("Jo'nab ketish nuqtasi: " + fromWhere.toString().toUpperCase() + System.lineSeparator() + "Borish manzilni kiriting.");
            sendMessage.setChatId(message.getChatId());
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }

        // choosing line toWhere
        else if (update.hasMessage() && correctLine(text) && !fromWhere.toString().equals(text)) {
            toWhere = new StringBuilder(message.getText());
            sendMessage.setText("Borish manzilingiz: " + toWhere.toString().toUpperCase());
            sendMessage.setChatId(message.getChatId());
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }





        // incorrect line
        if (update.hasMessage() && !correctLine(text) && !text.equals("/start")) {
            sendMessage.setText("Mavjud emas.");
            sendMessage.setChatId(message.getChatId());
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException();
            }
        }
                }
                }






