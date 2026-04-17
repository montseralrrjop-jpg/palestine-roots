package com.palestine.roots.data.local.db;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.palestine.roots.data.local.dao.SiteDao;
import com.palestine.roots.data.local.dao.SiteDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class PalestineDatabase_Impl extends PalestineDatabase {
  private volatile SiteDao _siteDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `sites` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `nameEn` TEXT NOT NULL, `city` TEXT NOT NULL, `cityEn` TEXT NOT NULL, `description` TEXT NOT NULL, `descriptionEn` TEXT NOT NULL, `history` TEXT NOT NULL, `historyEn` TEXT NOT NULL, `imageUrl` TEXT NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, `category` TEXT NOT NULL, `categoryEn` TEXT NOT NULL, `foundationYear` TEXT NOT NULL, `isFavorite` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '150ef9c4d5c5e028adccbd1f14fbebf0')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `sites`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsSites = new HashMap<String, TableInfo.Column>(16);
        _columnsSites.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSites.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSites.put("nameEn", new TableInfo.Column("nameEn", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSites.put("city", new TableInfo.Column("city", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSites.put("cityEn", new TableInfo.Column("cityEn", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSites.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSites.put("descriptionEn", new TableInfo.Column("descriptionEn", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSites.put("history", new TableInfo.Column("history", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSites.put("historyEn", new TableInfo.Column("historyEn", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSites.put("imageUrl", new TableInfo.Column("imageUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSites.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSites.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSites.put("category", new TableInfo.Column("category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSites.put("categoryEn", new TableInfo.Column("categoryEn", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSites.put("foundationYear", new TableInfo.Column("foundationYear", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSites.put("isFavorite", new TableInfo.Column("isFavorite", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSites = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSites = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSites = new TableInfo("sites", _columnsSites, _foreignKeysSites, _indicesSites);
        final TableInfo _existingSites = TableInfo.read(db, "sites");
        if (!_infoSites.equals(_existingSites)) {
          return new RoomOpenHelper.ValidationResult(false, "sites(com.palestine.roots.data.local.entity.SiteEntity).\n"
                  + " Expected:\n" + _infoSites + "\n"
                  + " Found:\n" + _existingSites);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "150ef9c4d5c5e028adccbd1f14fbebf0", "bb210d1add1bddf2f11af99aa505e69f");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "sites");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `sites`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(SiteDao.class, SiteDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public SiteDao siteDao() {
    if (_siteDao != null) {
      return _siteDao;
    } else {
      synchronized(this) {
        if(_siteDao == null) {
          _siteDao = new SiteDao_Impl(this);
        }
        return _siteDao;
      }
    }
  }
}
