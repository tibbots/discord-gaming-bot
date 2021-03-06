package io.github.codejanovic.discord.bot.inject;


import io.github.codejanovic.discord.bot.DiscordBot;
import io.github.codejanovic.discord.bot.inject.provider.DiscordApiProvider;
import io.github.codejanovic.discord.bot.inject.provider.FirestoreProvider;
import io.github.codejanovic.discord.bot.listener.*;
import io.github.codejanovic.discord.bot.listener.interests.MessageInterestFactory;
import io.github.codejanovic.discord.bot.repository.firestore.FirestoreAccountProviderRepository;
import io.github.codejanovic.discord.bot.repository.firestore.FirestoreAccountRepository;
import io.github.codejanovic.discord.bot.repository.firestore.FirestoreDocuments;
import io.github.codejanovic.discord.bot.repository.firestore.FirestoreUsersRepository;
import org.jusecase.inject.Injector;

public class Registry {
    public static final Registry instance = new Registry();

    private final Injector _injector = Injector.getInstance();

    private Registry() {
        addFirestoreMappers();
        addApis();
        addRepositories();
        addDiscordApiListeners();
    }

    private void addFirestoreMappers() {
        _injector.add(FirestoreDocuments.class);
    }


    private void addDiscordApiListeners() {
        _injector.add(CreateProfileListener.class);
        _injector.add(AddAccountListener.class);
        _injector.add(ShowProfileListener.class);
        _injector.add(ShowHelpListener.class);
        _injector.add(DeleteProfileListener.class);
    }

    private void addApis() {
        _injector.addProviderForSingleInstance(DiscordApiProvider.class);
        _injector.add(DiscordBot.Api.class);
        _injector.addProviderForSingleInstance(FirestoreProvider.class);
        _injector.add(MessageInterestFactory.class);
    }


    private void addServices() {
    }


    private void addRepositories() {
        _injector.add(FirestoreUsersRepository.class);
        _injector.add(FirestoreAccountProviderRepository.class);
        _injector.add(FirestoreAccountRepository.class);
    }


    public <T> T inject(final T entity, Class<?> clazz) {
        _injector.inject(entity, clazz);
        return entity;
    }


}
