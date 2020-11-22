package android.support.v4.provider;

import android.content.Context;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.DocumentsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.io.File;

public abstract class DocumentFile
{
  static final String TAG = "DocumentFile";
  @Nullable
  private final DocumentFile mParent;
  
  DocumentFile(@Nullable DocumentFile paramDocumentFile)
  {
    this.mParent = paramDocumentFile;
  }
  
  @NonNull
  public static DocumentFile fromFile(@NonNull File paramFile)
  {
    return new RawDocumentFile(null, paramFile);
  }
  
  @Nullable
  public static DocumentFile fromSingleUri(@NonNull Context paramContext, @NonNull Uri paramUri)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      return new SingleDocumentFile(null, paramContext, paramUri);
    }
    return null;
  }
  
  @Nullable
  public static DocumentFile fromTreeUri(@NonNull Context paramContext, @NonNull Uri paramUri)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return new TreeDocumentFile(null, paramContext, DocumentsContract.buildDocumentUriUsingTree(paramUri, DocumentsContract.getTreeDocumentId(paramUri)));
    }
    return null;
  }
  
  public static boolean isDocumentUri(@NonNull Context paramContext, @Nullable Uri paramUri)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      return DocumentsContract.isDocumentUri(paramContext, paramUri);
    }
    return false;
  }
  
  public abstract boolean canRead();
  
  public abstract boolean canWrite();
  
  @Nullable
  public abstract DocumentFile createDirectory(@NonNull String paramString);
  
  @Nullable
  public abstract DocumentFile createFile(@NonNull String paramString1, @NonNull String paramString2);
  
  public abstract boolean delete();
  
  public abstract boolean exists();
  
  @Nullable
  public DocumentFile findFile(@NonNull String paramString)
  {
    DocumentFile[] arrayOfDocumentFile = listFiles();
    int j = arrayOfDocumentFile.length;
    int i = 0;
    while (i < j)
    {
      DocumentFile localDocumentFile = arrayOfDocumentFile[i];
      if (paramString.equals(localDocumentFile.getName())) {
        return localDocumentFile;
      }
      i += 1;
    }
    return null;
  }
  
  @Nullable
  public abstract String getName();
  
  @Nullable
  public DocumentFile getParentFile()
  {
    return this.mParent;
  }
  
  @Nullable
  public abstract String getType();
  
  @NonNull
  public abstract Uri getUri();
  
  public abstract boolean isDirectory();
  
  public abstract boolean isFile();
  
  public abstract boolean isVirtual();
  
  public abstract long lastModified();
  
  public abstract long length();
  
  @NonNull
  public abstract DocumentFile[] listFiles();
  
  public abstract boolean renameTo(@NonNull String paramString);
}


/* Location:              C:\Users\lixia\Desktop\android反编译三件套\dex2jar-2.0\classes-dex2jar.jar!\android\support\v4\provider\DocumentFile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */