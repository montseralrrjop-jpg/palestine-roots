package com.palestine.roots.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.palestine.roots.data.local.entity.SiteEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class SiteDao_Impl implements SiteDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<SiteEntity> __insertionAdapterOfSiteEntity;

  private final SharedSQLiteStatement __preparedStmtOfUpdateFavoriteStatus;

  public SiteDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSiteEntity = new EntityInsertionAdapter<SiteEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `sites` (`id`,`name`,`nameEn`,`city`,`cityEn`,`description`,`descriptionEn`,`history`,`historyEn`,`imageUrl`,`latitude`,`longitude`,`category`,`categoryEn`,`foundationYear`,`isFavorite`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SiteEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getNameEn());
        statement.bindString(4, entity.getCity());
        statement.bindString(5, entity.getCityEn());
        statement.bindString(6, entity.getDescription());
        statement.bindString(7, entity.getDescriptionEn());
        statement.bindString(8, entity.getHistory());
        statement.bindString(9, entity.getHistoryEn());
        statement.bindString(10, entity.getImageUrl());
        statement.bindDouble(11, entity.getLatitude());
        statement.bindDouble(12, entity.getLongitude());
        statement.bindString(13, entity.getCategory());
        statement.bindString(14, entity.getCategoryEn());
        statement.bindString(15, entity.getFoundationYear());
        final int _tmp = entity.isFavorite() ? 1 : 0;
        statement.bindLong(16, _tmp);
      }
    };
    this.__preparedStmtOfUpdateFavoriteStatus = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE sites SET isFavorite = ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertSites(final List<SiteEntity> sites,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfSiteEntity.insert(sites);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateFavoriteStatus(final String siteId, final boolean isFavorite,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateFavoriteStatus.acquire();
        int _argIndex = 1;
        final int _tmp = isFavorite ? 1 : 0;
        _stmt.bindLong(_argIndex, _tmp);
        _argIndex = 2;
        _stmt.bindString(_argIndex, siteId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfUpdateFavoriteStatus.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<SiteEntity>> getAllSites() {
    final String _sql = "SELECT * FROM sites";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"sites"}, new Callable<List<SiteEntity>>() {
      @Override
      @NonNull
      public List<SiteEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfNameEn = CursorUtil.getColumnIndexOrThrow(_cursor, "nameEn");
          final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
          final int _cursorIndexOfCityEn = CursorUtil.getColumnIndexOrThrow(_cursor, "cityEn");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfDescriptionEn = CursorUtil.getColumnIndexOrThrow(_cursor, "descriptionEn");
          final int _cursorIndexOfHistory = CursorUtil.getColumnIndexOrThrow(_cursor, "history");
          final int _cursorIndexOfHistoryEn = CursorUtil.getColumnIndexOrThrow(_cursor, "historyEn");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfCategoryEn = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryEn");
          final int _cursorIndexOfFoundationYear = CursorUtil.getColumnIndexOrThrow(_cursor, "foundationYear");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final List<SiteEntity> _result = new ArrayList<SiteEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SiteEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpNameEn;
            _tmpNameEn = _cursor.getString(_cursorIndexOfNameEn);
            final String _tmpCity;
            _tmpCity = _cursor.getString(_cursorIndexOfCity);
            final String _tmpCityEn;
            _tmpCityEn = _cursor.getString(_cursorIndexOfCityEn);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpDescriptionEn;
            _tmpDescriptionEn = _cursor.getString(_cursorIndexOfDescriptionEn);
            final String _tmpHistory;
            _tmpHistory = _cursor.getString(_cursorIndexOfHistory);
            final String _tmpHistoryEn;
            _tmpHistoryEn = _cursor.getString(_cursorIndexOfHistoryEn);
            final String _tmpImageUrl;
            _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            final double _tmpLatitude;
            _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            final double _tmpLongitude;
            _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpCategoryEn;
            _tmpCategoryEn = _cursor.getString(_cursorIndexOfCategoryEn);
            final String _tmpFoundationYear;
            _tmpFoundationYear = _cursor.getString(_cursorIndexOfFoundationYear);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            _item = new SiteEntity(_tmpId,_tmpName,_tmpNameEn,_tmpCity,_tmpCityEn,_tmpDescription,_tmpDescriptionEn,_tmpHistory,_tmpHistoryEn,_tmpImageUrl,_tmpLatitude,_tmpLongitude,_tmpCategory,_tmpCategoryEn,_tmpFoundationYear,_tmpIsFavorite);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<SiteEntity>> getSitesByCity(final String cityName) {
    final String _sql = "SELECT * FROM sites WHERE city = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, cityName);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"sites"}, new Callable<List<SiteEntity>>() {
      @Override
      @NonNull
      public List<SiteEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfNameEn = CursorUtil.getColumnIndexOrThrow(_cursor, "nameEn");
          final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
          final int _cursorIndexOfCityEn = CursorUtil.getColumnIndexOrThrow(_cursor, "cityEn");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfDescriptionEn = CursorUtil.getColumnIndexOrThrow(_cursor, "descriptionEn");
          final int _cursorIndexOfHistory = CursorUtil.getColumnIndexOrThrow(_cursor, "history");
          final int _cursorIndexOfHistoryEn = CursorUtil.getColumnIndexOrThrow(_cursor, "historyEn");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfCategoryEn = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryEn");
          final int _cursorIndexOfFoundationYear = CursorUtil.getColumnIndexOrThrow(_cursor, "foundationYear");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final List<SiteEntity> _result = new ArrayList<SiteEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SiteEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpNameEn;
            _tmpNameEn = _cursor.getString(_cursorIndexOfNameEn);
            final String _tmpCity;
            _tmpCity = _cursor.getString(_cursorIndexOfCity);
            final String _tmpCityEn;
            _tmpCityEn = _cursor.getString(_cursorIndexOfCityEn);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpDescriptionEn;
            _tmpDescriptionEn = _cursor.getString(_cursorIndexOfDescriptionEn);
            final String _tmpHistory;
            _tmpHistory = _cursor.getString(_cursorIndexOfHistory);
            final String _tmpHistoryEn;
            _tmpHistoryEn = _cursor.getString(_cursorIndexOfHistoryEn);
            final String _tmpImageUrl;
            _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            final double _tmpLatitude;
            _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            final double _tmpLongitude;
            _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpCategoryEn;
            _tmpCategoryEn = _cursor.getString(_cursorIndexOfCategoryEn);
            final String _tmpFoundationYear;
            _tmpFoundationYear = _cursor.getString(_cursorIndexOfFoundationYear);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            _item = new SiteEntity(_tmpId,_tmpName,_tmpNameEn,_tmpCity,_tmpCityEn,_tmpDescription,_tmpDescriptionEn,_tmpHistory,_tmpHistoryEn,_tmpImageUrl,_tmpLatitude,_tmpLongitude,_tmpCategory,_tmpCategoryEn,_tmpFoundationYear,_tmpIsFavorite);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getSiteById(final String siteId,
      final Continuation<? super SiteEntity> $completion) {
    final String _sql = "SELECT * FROM sites WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, siteId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<SiteEntity>() {
      @Override
      @Nullable
      public SiteEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfNameEn = CursorUtil.getColumnIndexOrThrow(_cursor, "nameEn");
          final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
          final int _cursorIndexOfCityEn = CursorUtil.getColumnIndexOrThrow(_cursor, "cityEn");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfDescriptionEn = CursorUtil.getColumnIndexOrThrow(_cursor, "descriptionEn");
          final int _cursorIndexOfHistory = CursorUtil.getColumnIndexOrThrow(_cursor, "history");
          final int _cursorIndexOfHistoryEn = CursorUtil.getColumnIndexOrThrow(_cursor, "historyEn");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfCategoryEn = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryEn");
          final int _cursorIndexOfFoundationYear = CursorUtil.getColumnIndexOrThrow(_cursor, "foundationYear");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final SiteEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpNameEn;
            _tmpNameEn = _cursor.getString(_cursorIndexOfNameEn);
            final String _tmpCity;
            _tmpCity = _cursor.getString(_cursorIndexOfCity);
            final String _tmpCityEn;
            _tmpCityEn = _cursor.getString(_cursorIndexOfCityEn);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpDescriptionEn;
            _tmpDescriptionEn = _cursor.getString(_cursorIndexOfDescriptionEn);
            final String _tmpHistory;
            _tmpHistory = _cursor.getString(_cursorIndexOfHistory);
            final String _tmpHistoryEn;
            _tmpHistoryEn = _cursor.getString(_cursorIndexOfHistoryEn);
            final String _tmpImageUrl;
            _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            final double _tmpLatitude;
            _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            final double _tmpLongitude;
            _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpCategoryEn;
            _tmpCategoryEn = _cursor.getString(_cursorIndexOfCategoryEn);
            final String _tmpFoundationYear;
            _tmpFoundationYear = _cursor.getString(_cursorIndexOfFoundationYear);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            _result = new SiteEntity(_tmpId,_tmpName,_tmpNameEn,_tmpCity,_tmpCityEn,_tmpDescription,_tmpDescriptionEn,_tmpHistory,_tmpHistoryEn,_tmpImageUrl,_tmpLatitude,_tmpLongitude,_tmpCategory,_tmpCategoryEn,_tmpFoundationYear,_tmpIsFavorite);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<SiteEntity>> searchSites(final String query) {
    final String _sql = "SELECT * FROM sites WHERE name LIKE '%' || ? || '%' OR description LIKE '%' || ? || '%'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, query);
    _argIndex = 2;
    _statement.bindString(_argIndex, query);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"sites"}, new Callable<List<SiteEntity>>() {
      @Override
      @NonNull
      public List<SiteEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfNameEn = CursorUtil.getColumnIndexOrThrow(_cursor, "nameEn");
          final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
          final int _cursorIndexOfCityEn = CursorUtil.getColumnIndexOrThrow(_cursor, "cityEn");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfDescriptionEn = CursorUtil.getColumnIndexOrThrow(_cursor, "descriptionEn");
          final int _cursorIndexOfHistory = CursorUtil.getColumnIndexOrThrow(_cursor, "history");
          final int _cursorIndexOfHistoryEn = CursorUtil.getColumnIndexOrThrow(_cursor, "historyEn");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfCategoryEn = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryEn");
          final int _cursorIndexOfFoundationYear = CursorUtil.getColumnIndexOrThrow(_cursor, "foundationYear");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final List<SiteEntity> _result = new ArrayList<SiteEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SiteEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpNameEn;
            _tmpNameEn = _cursor.getString(_cursorIndexOfNameEn);
            final String _tmpCity;
            _tmpCity = _cursor.getString(_cursorIndexOfCity);
            final String _tmpCityEn;
            _tmpCityEn = _cursor.getString(_cursorIndexOfCityEn);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpDescriptionEn;
            _tmpDescriptionEn = _cursor.getString(_cursorIndexOfDescriptionEn);
            final String _tmpHistory;
            _tmpHistory = _cursor.getString(_cursorIndexOfHistory);
            final String _tmpHistoryEn;
            _tmpHistoryEn = _cursor.getString(_cursorIndexOfHistoryEn);
            final String _tmpImageUrl;
            _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            final double _tmpLatitude;
            _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            final double _tmpLongitude;
            _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpCategoryEn;
            _tmpCategoryEn = _cursor.getString(_cursorIndexOfCategoryEn);
            final String _tmpFoundationYear;
            _tmpFoundationYear = _cursor.getString(_cursorIndexOfFoundationYear);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            _item = new SiteEntity(_tmpId,_tmpName,_tmpNameEn,_tmpCity,_tmpCityEn,_tmpDescription,_tmpDescriptionEn,_tmpHistory,_tmpHistoryEn,_tmpImageUrl,_tmpLatitude,_tmpLongitude,_tmpCategory,_tmpCategoryEn,_tmpFoundationYear,_tmpIsFavorite);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<SiteEntity>> getSitesByCategory(final String category) {
    final String _sql = "SELECT * FROM sites WHERE category = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, category);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"sites"}, new Callable<List<SiteEntity>>() {
      @Override
      @NonNull
      public List<SiteEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfNameEn = CursorUtil.getColumnIndexOrThrow(_cursor, "nameEn");
          final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
          final int _cursorIndexOfCityEn = CursorUtil.getColumnIndexOrThrow(_cursor, "cityEn");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfDescriptionEn = CursorUtil.getColumnIndexOrThrow(_cursor, "descriptionEn");
          final int _cursorIndexOfHistory = CursorUtil.getColumnIndexOrThrow(_cursor, "history");
          final int _cursorIndexOfHistoryEn = CursorUtil.getColumnIndexOrThrow(_cursor, "historyEn");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfCategoryEn = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryEn");
          final int _cursorIndexOfFoundationYear = CursorUtil.getColumnIndexOrThrow(_cursor, "foundationYear");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final List<SiteEntity> _result = new ArrayList<SiteEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SiteEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpNameEn;
            _tmpNameEn = _cursor.getString(_cursorIndexOfNameEn);
            final String _tmpCity;
            _tmpCity = _cursor.getString(_cursorIndexOfCity);
            final String _tmpCityEn;
            _tmpCityEn = _cursor.getString(_cursorIndexOfCityEn);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpDescriptionEn;
            _tmpDescriptionEn = _cursor.getString(_cursorIndexOfDescriptionEn);
            final String _tmpHistory;
            _tmpHistory = _cursor.getString(_cursorIndexOfHistory);
            final String _tmpHistoryEn;
            _tmpHistoryEn = _cursor.getString(_cursorIndexOfHistoryEn);
            final String _tmpImageUrl;
            _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            final double _tmpLatitude;
            _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            final double _tmpLongitude;
            _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpCategoryEn;
            _tmpCategoryEn = _cursor.getString(_cursorIndexOfCategoryEn);
            final String _tmpFoundationYear;
            _tmpFoundationYear = _cursor.getString(_cursorIndexOfFoundationYear);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            _item = new SiteEntity(_tmpId,_tmpName,_tmpNameEn,_tmpCity,_tmpCityEn,_tmpDescription,_tmpDescriptionEn,_tmpHistory,_tmpHistoryEn,_tmpImageUrl,_tmpLatitude,_tmpLongitude,_tmpCategory,_tmpCategoryEn,_tmpFoundationYear,_tmpIsFavorite);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<SiteEntity>> getFavoriteSites() {
    final String _sql = "SELECT * FROM sites WHERE isFavorite = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"sites"}, new Callable<List<SiteEntity>>() {
      @Override
      @NonNull
      public List<SiteEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfNameEn = CursorUtil.getColumnIndexOrThrow(_cursor, "nameEn");
          final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
          final int _cursorIndexOfCityEn = CursorUtil.getColumnIndexOrThrow(_cursor, "cityEn");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfDescriptionEn = CursorUtil.getColumnIndexOrThrow(_cursor, "descriptionEn");
          final int _cursorIndexOfHistory = CursorUtil.getColumnIndexOrThrow(_cursor, "history");
          final int _cursorIndexOfHistoryEn = CursorUtil.getColumnIndexOrThrow(_cursor, "historyEn");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfCategoryEn = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryEn");
          final int _cursorIndexOfFoundationYear = CursorUtil.getColumnIndexOrThrow(_cursor, "foundationYear");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final List<SiteEntity> _result = new ArrayList<SiteEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SiteEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpNameEn;
            _tmpNameEn = _cursor.getString(_cursorIndexOfNameEn);
            final String _tmpCity;
            _tmpCity = _cursor.getString(_cursorIndexOfCity);
            final String _tmpCityEn;
            _tmpCityEn = _cursor.getString(_cursorIndexOfCityEn);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpDescriptionEn;
            _tmpDescriptionEn = _cursor.getString(_cursorIndexOfDescriptionEn);
            final String _tmpHistory;
            _tmpHistory = _cursor.getString(_cursorIndexOfHistory);
            final String _tmpHistoryEn;
            _tmpHistoryEn = _cursor.getString(_cursorIndexOfHistoryEn);
            final String _tmpImageUrl;
            _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            final double _tmpLatitude;
            _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            final double _tmpLongitude;
            _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpCategoryEn;
            _tmpCategoryEn = _cursor.getString(_cursorIndexOfCategoryEn);
            final String _tmpFoundationYear;
            _tmpFoundationYear = _cursor.getString(_cursorIndexOfFoundationYear);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            _item = new SiteEntity(_tmpId,_tmpName,_tmpNameEn,_tmpCity,_tmpCityEn,_tmpDescription,_tmpDescriptionEn,_tmpHistory,_tmpHistoryEn,_tmpImageUrl,_tmpLatitude,_tmpLongitude,_tmpCategory,_tmpCategoryEn,_tmpFoundationYear,_tmpIsFavorite);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
