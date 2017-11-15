package info.gokit.androidarch.db;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.database.DatabaseUtils;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.inject.Singleton;

import static info.gokit.androidarch.db.AppDatabase.DATABASE_NAME;

/**
 * Created by llitfkitfk on 11/14/17.
 */

@Singleton
public class DatabaseCreator {
    private static DatabaseCreator sInstance;

    // For Singleton instantiation
    private static final Object LOCK = new Object();

    private MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();
    private AppDatabase mDB;

    private final AtomicBoolean mInitiializing = new AtomicBoolean(true);

    public synchronized static DatabaseCreator getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = new DatabaseCreator();
                }
            }
        }
        return sInstance;
    }

    public LiveData<Boolean> isDatabaseCreated() {
        return mIsDatabaseCreated;
    }

    @Nullable
    public AppDatabase getDatabase() {
        return mDB;
    }

    @SuppressLint("StaticFieldLeak")
    public void createDB(final Context context) {

        Log.d("DatabaseCreator", "Creating DB from " + Thread.currentThread().getName());


        if (!mInitiializing.compareAndSet(true, false)) {
            return;
        }
        mIsDatabaseCreated.setValue(false);
        new AsyncTask<Context, Void, Void>() {

            @Override
            protected Void doInBackground(Context... params) {

                Log.d("DatabaseCreator", "Starting bg job " + Thread.currentThread().getName());

                Context context = params[0].getApplicationContext();
                context.deleteDatabase(DATABASE_NAME);
                AppDatabase db = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME).build();

                addDelay();

                DatabaseUtil.initializeDB(db);
                Log.d("DatabaseCreator", "DB was populated in thread " + Thread.currentThread().getName());
                mDB = db;
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                // Now on the main thread, notify observers that the db is created and ready.
                mIsDatabaseCreated.setValue(true);
            }
        }.execute(context.getApplicationContext());
    }

    private void addDelay() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ignored) {}
    }
}
