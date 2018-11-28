package io.github.codejanovic.discord.bot.inject;


import io.github.codejanovic.discord.bot.DiscordBot;
import io.github.codejanovic.discord.bot.inject.provider.DiscordApiProvider;
import io.github.codejanovic.discord.bot.inject.provider.FirestoreProvider;
import io.github.codejanovic.discord.bot.listener.CreateProfileListener;
import io.github.codejanovic.discord.bot.repository.FirestoreDocuments;
import io.github.codejanovic.discord.bot.repository.FirestoreUsersRepository;
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
    }

    private void addApis() {
        _injector.addProviderForSingleInstance(DiscordApiProvider.class);
        _injector.add(DiscordBot.BySystemProperty.class);
        _injector.addProviderForSingleInstance(FirestoreProvider.class);
    }


    private void addServices() {
    }


    private void addRepositories() {
        _injector.add(FirestoreUsersRepository.class);
    }


    public <T> T inject(final T entity, Class<?> clazz) {
        _injector.inject(entity, clazz);
        return entity;
    }


}
