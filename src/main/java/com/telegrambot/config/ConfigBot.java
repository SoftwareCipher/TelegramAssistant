package com.telegrambot.config;

import lombok.Getter;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Getter
public class ConfigBot extends TelegramLongPollingBot {

    private Long userId;
    @Override
    public String getBotUsername() {
        return "VirtualTelegramAssistant";
    }

    @Override
    public String getBotToken() {
        return "7039458777:AAEQw_DRLhgusX79kObb5FeD52cws6X7d4U";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            var msg = update.getMessage();
            var user = msg.getFrom();
            var chatId = msg.getChatId();
            var text = msg.getText();

            if ("/start".equals(text)) {
                sendText(chatId, "Welcome, " + user.getFirstName() + "! How can I assist you today?");
            }
        }
    }

    public void sendText(Long who, String what) {
        if (who != null) {
            SendMessage sm = SendMessage.builder()
                    .chatId(who.toString())
                    .text(what)
                    .build();
            try {
                execute(sm);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Cannot send message because chatId is null.");
        }
    }
}
