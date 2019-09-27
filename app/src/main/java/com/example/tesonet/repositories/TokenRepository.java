package com.example.tesonet.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.tesonet.database.TokenDatabase;
import com.example.tesonet.database.daos.TokenDao;
import com.example.tesonet.database.models.Token;


public class TokenRepository {
    private TokenDao tokenDao;
    private LiveData<Token> token;

    public TokenRepository(Application application) {
        TokenDatabase database = TokenDatabase.getInstance(application);
        tokenDao = database.tokenDao();
        token = tokenDao.getTokenValue();
    }

    public void insert(Token token) {
        new InsertTokenAsyncTask(tokenDao).execute(token);
    }

    public void update(Token token){
        new UpdateTokenAsyncTask(tokenDao).execute(token);
    }

    public void deleteToken() {
        new DeleteTokenAsyncTask(tokenDao).execute();
    }

    public LiveData<Token> getTokenValue() {
        return token;
    }

    private static class InsertTokenAsyncTask extends AsyncTask<Token, Void, Void> {
        private TokenDao tokenDao;

        private InsertTokenAsyncTask(TokenDao tokenDao) {
            this.tokenDao = tokenDao;
        }

        @Override
        protected Void doInBackground(Token... tokens) {
            tokenDao.insert(tokens[0]);
            return null;
        }
    }

    private static class UpdateTokenAsyncTask extends AsyncTask<Token, Void, Void>{
        private TokenDao tokenDao;

        private UpdateTokenAsyncTask(TokenDao tokenDao) {
            this.tokenDao = tokenDao;
        }

        @Override
        protected Void doInBackground(Token... tokens) {
            tokenDao.update(tokens[0]);
            return null;
        }
    }

    private static class DeleteTokenAsyncTask extends AsyncTask<Token, Void, Void> {
        private TokenDao tokenDao;

        private DeleteTokenAsyncTask(TokenDao tokenDao) {
            this.tokenDao = tokenDao;
        }

        @Override
        protected Void doInBackground(Token... tokens) {
            tokenDao.deleteToken();
            return null;
        }
    }
}
