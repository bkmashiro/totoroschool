package android.support.v4.graphics.drawable;

import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.util.Preconditions;
import android.text.TextUtils;
import android.util.Log;
import androidx.versionedparcelable.CustomVersionedParcelable;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;

public class IconCompat
  extends CustomVersionedParcelable
{
  private static final float ADAPTIVE_ICON_INSET_FACTOR = 0.25F;
  private static final int AMBIENT_SHADOW_ALPHA = 30;
  private static final float BLUR_FACTOR = 0.010416667F;
  static final PorterDuff.Mode DEFAULT_TINT_MODE = PorterDuff.Mode.SRC_IN;
  private static final float DEFAULT_VIEW_PORT_SCALE = 0.6666667F;
  private static final String EXTRA_INT1 = "int1";
  private static final String EXTRA_INT2 = "int2";
  private static final String EXTRA_OBJ = "obj";
  private static final String EXTRA_TINT_LIST = "tint_list";
  private static final String EXTRA_TINT_MODE = "tint_mode";
  private static final String EXTRA_TYPE = "type";
  private static final float ICON_DIAMETER_FACTOR = 0.9166667F;
  private static final int KEY_SHADOW_ALPHA = 61;
  private static final float KEY_SHADOW_OFFSET_FACTOR = 0.020833334F;
  private static final String TAG = "IconCompat";
  public static final int TYPE_UNKNOWN = -1;
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public byte[] mData;
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public int mInt1;
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public int mInt2;
  Object mObj1;
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public Parcelable mParcelable;
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public ColorStateList mTintList = null;
  PorterDuff.Mode mTintMode = DEFAULT_TINT_MODE;
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public String mTintModeStr;
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public int mType;
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public IconCompat() {}
  
  private IconCompat(int paramInt)
  {
    this.mType = paramInt;
  }
  
  @Nullable
  public static IconCompat createFromBundle(@NonNull Bundle paramBundle)
  {
    int i = paramBundle.getInt("type");
    IconCompat localIconCompat = new IconCompat(i);
    localIconCompat.mInt1 = paramBundle.getInt("int1");
    localIconCompat.mInt2 = paramBundle.getInt("int2");
    if (paramBundle.containsKey("tint_list")) {
      localIconCompat.mTintList = ((ColorStateList)paramBundle.getParcelable("tint_list"));
    }
    if (paramBundle.containsKey("tint_mode")) {
      localIconCompat.mTintMode = PorterDuff.Mode.valueOf(paramBundle.getString("tint_mode"));
    }
    if (i != -1) {
      switch (i)
      {
      default: 
        paramBundle = new StringBuilder();
        paramBundle.append("Unknown type ");
        paramBundle.append(i);
        Log.w("IconCompat", paramBundle.toString());
        return null;
      case 3: 
        localIconCompat.mObj1 = paramBundle.getByteArray("obj");
        return localIconCompat;
      case 2: 
      case 4: 
        localIconCompat.mObj1 = paramBundle.getString("obj");
        return localIconCompat;
      }
    }
    localIconCompat.mObj1 = paramBundle.getParcelable("obj");
    return localIconCompat;
  }
  
  @Nullable
  @RequiresApi(23)
  public static IconCompat createFromIcon(@NonNull Context paramContext, @NonNull Icon paramIcon)
  {
    Preconditions.checkNotNull(paramIcon);
    int i = getType(paramIcon);
    if (i != 2)
    {
      if (i != 4)
      {
        paramContext = new IconCompat(-1);
        paramContext.mObj1 = paramIcon;
        return paramContext;
      }
      return createWithContentUri(getUri(paramIcon));
    }
    String str = getResPackage(paramIcon);
    try
    {
      paramContext = createWithResource(getResources(paramContext, str), str, getResId(paramIcon));
      return paramContext;
    }
    catch (Resources.NotFoundException paramContext)
    {
      for (;;) {}
    }
    throw new IllegalArgumentException("Icon resource cannot be found");
  }
  
  @Nullable
  @RequiresApi(23)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static IconCompat createFromIcon(@NonNull Icon paramIcon)
  {
    Preconditions.checkNotNull(paramIcon);
    int i = getType(paramIcon);
    if (i != 2)
    {
      if (i != 4)
      {
        IconCompat localIconCompat = new IconCompat(-1);
        localIconCompat.mObj1 = paramIcon;
        return localIconCompat;
      }
      return createWithContentUri(getUri(paramIcon));
    }
    return createWithResource(null, getResPackage(paramIcon), getResId(paramIcon));
  }
  
  @VisibleForTesting
  static Bitmap createLegacyIconFromAdaptiveIcon(Bitmap paramBitmap, boolean paramBoolean)
  {
    int i = (int)(Math.min(paramBitmap.getWidth(), paramBitmap.getHeight()) * 0.6666667F);
    Bitmap localBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    Paint localPaint = new Paint(3);
    float f1 = i;
    float f2 = 0.5F * f1;
    float f3 = 0.9166667F * f2;
    if (paramBoolean)
    {
      float f4 = 0.010416667F * f1;
      localPaint.setColor(0);
      localPaint.setShadowLayer(f4, 0.0F, f1 * 0.020833334F, 1023410176);
      localCanvas.drawCircle(f2, f2, f3, localPaint);
      localPaint.setShadowLayer(f4, 0.0F, 0.0F, 503316480);
      localCanvas.drawCircle(f2, f2, f3, localPaint);
      localPaint.clearShadowLayer();
    }
    localPaint.setColor(-16777216);
    BitmapShader localBitmapShader = new BitmapShader(paramBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
    Matrix localMatrix = new Matrix();
    localMatrix.setTranslate(-(paramBitmap.getWidth() - i) / 2, -(paramBitmap.getHeight() - i) / 2);
    localBitmapShader.setLocalMatrix(localMatrix);
    localPaint.setShader(localBitmapShader);
    localCanvas.drawCircle(f2, f2, f3, localPaint);
    localCanvas.setBitmap(null);
    return localBitmap;
  }
  
  public static IconCompat createWithAdaptiveBitmap(Bitmap paramBitmap)
  {
    if (paramBitmap != null)
    {
      IconCompat localIconCompat = new IconCompat(5);
      localIconCompat.mObj1 = paramBitmap;
      return localIconCompat;
    }
    throw new IllegalArgumentException("Bitmap must not be null.");
  }
  
  public static IconCompat createWithBitmap(Bitmap paramBitmap)
  {
    if (paramBitmap != null)
    {
      IconCompat localIconCompat = new IconCompat(1);
      localIconCompat.mObj1 = paramBitmap;
      return localIconCompat;
    }
    throw new IllegalArgumentException("Bitmap must not be null.");
  }
  
  public static IconCompat createWithContentUri(Uri paramUri)
  {
    if (paramUri != null) {
      return createWithContentUri(paramUri.toString());
    }
    throw new IllegalArgumentException("Uri must not be null.");
  }
  
  public static IconCompat createWithContentUri(String paramString)
  {
    if (paramString != null)
    {
      IconCompat localIconCompat = new IconCompat(4);
      localIconCompat.mObj1 = paramString;
      return localIconCompat;
    }
    throw new IllegalArgumentException("Uri must not be null.");
  }
  
  public static IconCompat createWithData(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramArrayOfByte != null)
    {
      IconCompat localIconCompat = new IconCompat(3);
      localIconCompat.mObj1 = paramArrayOfByte;
      localIconCompat.mInt1 = paramInt1;
      localIconCompat.mInt2 = paramInt2;
      return localIconCompat;
    }
    throw new IllegalArgumentException("Data must not be null.");
  }
  
  public static IconCompat createWithResource(Context paramContext, @DrawableRes int paramInt)
  {
    if (paramContext != null) {
      return createWithResource(paramContext.getResources(), paramContext.getPackageName(), paramInt);
    }
    throw new IllegalArgumentException("Context must not be null.");
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public static IconCompat createWithResource(Resources paramResources, String paramString, @DrawableRes int paramInt)
  {
    IconCompat localIconCompat;
    if (paramString != null) {
      if (paramInt != 0)
      {
        localIconCompat = new IconCompat(2);
        localIconCompat.mInt1 = paramInt;
        if (paramResources == null) {}
      }
    }
    try
    {
      localIconCompat.mObj1 = paramResources.getResourceName(paramInt);
      return localIconCompat;
    }
    catch (Resources.NotFoundException paramResources)
    {
      for (;;) {}
    }
    throw new IllegalArgumentException("Icon resource cannot be found");
    localIconCompat.mObj1 = paramString;
    return localIconCompat;
    throw new IllegalArgumentException("Drawable resource ID must not be 0");
    throw new IllegalArgumentException("Package must not be null.");
  }
  
  @DrawableRes
  @IdRes
  @RequiresApi(23)
  private static int getResId(@NonNull Icon paramIcon)
  {
    if (Build.VERSION.SDK_INT >= 28) {
      return paramIcon.getResId();
    }
    try
    {
      int i = ((Integer)paramIcon.getClass().getMethod("getResId", new Class[0]).invoke(paramIcon, new Object[0])).intValue();
      return i;
    }
    catch (NoSuchMethodException paramIcon)
    {
      Log.e("IconCompat", "Unable to get icon resource", paramIcon);
      return 0;
    }
    catch (InvocationTargetException paramIcon)
    {
      Log.e("IconCompat", "Unable to get icon resource", paramIcon);
      return 0;
    }
    catch (IllegalAccessException paramIcon)
    {
      Log.e("IconCompat", "Unable to get icon resource", paramIcon);
    }
    return 0;
  }
  
  @Nullable
  @RequiresApi(23)
  private static String getResPackage(@NonNull Icon paramIcon)
  {
    if (Build.VERSION.SDK_INT >= 28) {
      return paramIcon.getResPackage();
    }
    try
    {
      paramIcon = (String)paramIcon.getClass().getMethod("getResPackage", new Class[0]).invoke(paramIcon, new Object[0]);
      return paramIcon;
    }
    catch (NoSuchMethodException paramIcon)
    {
      Log.e("IconCompat", "Unable to get icon package", paramIcon);
      return null;
    }
    catch (InvocationTargetException paramIcon)
    {
      Log.e("IconCompat", "Unable to get icon package", paramIcon);
      return null;
    }
    catch (IllegalAccessException paramIcon)
    {
      Log.e("IconCompat", "Unable to get icon package", paramIcon);
    }
    return null;
  }
  
  private static Resources getResources(Context paramContext, String paramString)
  {
    if ("android".equals(paramString)) {
      return Resources.getSystem();
    }
    paramContext = paramContext.getPackageManager();
    try
    {
      ApplicationInfo localApplicationInfo = paramContext.getApplicationInfo(paramString, 8192);
      if (localApplicationInfo != null)
      {
        paramContext = paramContext.getResourcesForApplication(localApplicationInfo);
        return paramContext;
      }
      return null;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      Log.e("IconCompat", String.format("Unable to find pkg=%s for icon", new Object[] { paramString }), paramContext);
    }
    return null;
  }
  
  @RequiresApi(23)
  private static int getType(@NonNull Icon paramIcon)
  {
    if (Build.VERSION.SDK_INT >= 28) {
      return paramIcon.getType();
    }
    try
    {
      int i = ((Integer)paramIcon.getClass().getMethod("getType", new Class[0]).invoke(paramIcon, new Object[0])).intValue();
      return i;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Unable to get icon type ");
      localStringBuilder.append(paramIcon);
      Log.e("IconCompat", localStringBuilder.toString(), localNoSuchMethodException);
      return -1;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Unable to get icon type ");
      localStringBuilder.append(paramIcon);
      Log.e("IconCompat", localStringBuilder.toString(), localInvocationTargetException);
      return -1;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Unable to get icon type ");
      localStringBuilder.append(paramIcon);
      Log.e("IconCompat", localStringBuilder.toString(), localIllegalAccessException);
    }
    return -1;
  }
  
  @Nullable
  @RequiresApi(23)
  private static Uri getUri(@NonNull Icon paramIcon)
  {
    if (Build.VERSION.SDK_INT >= 28) {
      return paramIcon.getUri();
    }
    try
    {
      paramIcon = (Uri)paramIcon.getClass().getMethod("getUri", new Class[0]).invoke(paramIcon, new Object[0]);
      return paramIcon;
    }
    catch (NoSuchMethodException paramIcon)
    {
      Log.e("IconCompat", "Unable to get icon uri", paramIcon);
      return null;
    }
    catch (InvocationTargetException paramIcon)
    {
      Log.e("IconCompat", "Unable to get icon uri", paramIcon);
      return null;
    }
    catch (IllegalAccessException paramIcon)
    {
      Log.e("IconCompat", "Unable to get icon uri", paramIcon);
    }
    return null;
  }
  
  private Drawable loadDrawableInner(Context paramContext)
  {
    Object localObject3;
    label220:
    Object localObject2;
    switch (this.mType)
    {
    default: 
      return null;
    case 5: 
      return new BitmapDrawable(paramContext.getResources(), createLegacyIconFromAdaptiveIcon((Bitmap)this.mObj1, false));
    case 4: 
      localObject3 = Uri.parse((String)this.mObj1);
      Object localObject1 = ((Uri)localObject3).getScheme();
      StringBuilder localStringBuilder;
      if ((!"content".equals(localObject1)) && (!"file".equals(localObject1))) {
        try
        {
          localObject1 = new FileInputStream(new File((String)this.mObj1));
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("Unable to load image from path: ");
          localStringBuilder.append(localObject3);
          Log.w("IconCompat", localStringBuilder.toString(), localFileNotFoundException);
          break label220;
        }
      } else {
        try
        {
          InputStream localInputStream = paramContext.getContentResolver().openInputStream((Uri)localObject3);
        }
        catch (Exception localException)
        {
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("Unable to load image from URI: ");
          localStringBuilder.append(localObject3);
          Log.w("IconCompat", localStringBuilder.toString(), localException);
        }
      }
      localObject2 = null;
      if (localObject2 == null) {
        break label368;
      }
      return new BitmapDrawable(paramContext.getResources(), BitmapFactory.decodeStream((InputStream)localObject2));
    case 3: 
      return new BitmapDrawable(paramContext.getResources(), BitmapFactory.decodeByteArray((byte[])this.mObj1, this.mInt1, this.mInt2));
    case 2: 
      localObject3 = getResPackage();
      localObject2 = localObject3;
      if (TextUtils.isEmpty((CharSequence)localObject3)) {
        localObject2 = paramContext.getPackageName();
      }
      localObject2 = getResources(paramContext, (String)localObject2);
      try
      {
        paramContext = ResourcesCompat.getDrawable((Resources)localObject2, this.mInt1, paramContext.getTheme());
        return paramContext;
      }
      catch (RuntimeException paramContext)
      {
        Log.e("IconCompat", String.format("Unable to load resource 0x%08x from pkg=%s", new Object[] { Integer.valueOf(this.mInt1), this.mObj1 }), paramContext);
        return null;
      }
    }
    return new BitmapDrawable(paramContext.getResources(), (Bitmap)this.mObj1);
    label368:
    return null;
  }
  
  private static String typeToString(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "UNKNOWN";
    case 5: 
      return "BITMAP_MASKABLE";
    case 4: 
      return "URI";
    case 3: 
      return "DATA";
    case 2: 
      return "RESOURCE";
    }
    return "BITMAP";
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public void addToShortcutIntent(@NonNull Intent paramIntent, @Nullable Drawable paramDrawable, @NonNull Context paramContext)
  {
    checkResource(paramContext);
    int i = this.mType;
    if (i != 5)
    {
      switch (i)
      {
      default: 
        throw new IllegalArgumentException("Icon type not supported for intent shortcuts");
      case 2: 
        try
        {
          paramContext = paramContext.createPackageContext(getResPackage(), 0);
          if (paramDrawable == null)
          {
            paramIntent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(paramContext, this.mInt1));
            return;
          }
          localObject = ContextCompat.getDrawable(paramContext, this.mInt1);
          if ((((Drawable)localObject).getIntrinsicWidth() > 0) && (((Drawable)localObject).getIntrinsicHeight() > 0))
          {
            paramContext = Bitmap.createBitmap(((Drawable)localObject).getIntrinsicWidth(), ((Drawable)localObject).getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
          }
          else
          {
            i = ((ActivityManager)paramContext.getSystemService("activity")).getLauncherLargeIconSize();
            paramContext = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
          }
          ((Drawable)localObject).setBounds(0, 0, paramContext.getWidth(), paramContext.getHeight());
          ((Drawable)localObject).draw(new Canvas(paramContext));
        }
        catch (PackageManager.NameNotFoundException paramIntent)
        {
          paramDrawable = new StringBuilder();
          paramDrawable.append("Can't find package ");
          paramDrawable.append(this.mObj1);
          throw new IllegalArgumentException(paramDrawable.toString(), paramIntent);
        }
      }
      Object localObject = (Bitmap)this.mObj1;
      paramContext = (Context)localObject;
      if (paramDrawable != null) {
        paramContext = ((Bitmap)localObject).copy(((Bitmap)localObject).getConfig(), true);
      }
    }
    else
    {
      paramContext = createLegacyIconFromAdaptiveIcon((Bitmap)this.mObj1, true);
    }
    if (paramDrawable != null)
    {
      i = paramContext.getWidth();
      int j = paramContext.getHeight();
      paramDrawable.setBounds(i / 2, j / 2, i, j);
      paramDrawable.draw(new Canvas(paramContext));
    }
    paramIntent.putExtra("android.intent.extra.shortcut.ICON", paramContext);
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public void checkResource(Context paramContext)
  {
    if (this.mType == 2)
    {
      String str3 = (String)this.mObj1;
      if (!str3.contains(":")) {
        return;
      }
      String str2 = str3.split(":", -1)[1];
      String str1 = str2.split("/", -1)[0];
      str2 = str2.split("/", -1)[1];
      str3 = str3.split(":", -1)[0];
      int i = getResources(paramContext, str3).getIdentifier(str2, str1, str3);
      if (this.mInt1 != i)
      {
        paramContext = new StringBuilder();
        paramContext.append("Id has changed for ");
        paramContext.append(str3);
        paramContext.append("/");
        paramContext.append(str2);
        Log.i("IconCompat", paramContext.toString());
        this.mInt1 = i;
      }
    }
  }
  
  @IdRes
  public int getResId()
  {
    if ((this.mType == -1) && (Build.VERSION.SDK_INT >= 23)) {
      return getResId((Icon)this.mObj1);
    }
    if (this.mType == 2) {
      return this.mInt1;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("called getResId() on ");
    localStringBuilder.append(this);
    throw new IllegalStateException(localStringBuilder.toString());
  }
  
  @NonNull
  public String getResPackage()
  {
    if ((this.mType == -1) && (Build.VERSION.SDK_INT >= 23)) {
      return getResPackage((Icon)this.mObj1);
    }
    if (this.mType == 2) {
      return ((String)this.mObj1).split(":", -1)[0];
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("called getResPackage() on ");
    localStringBuilder.append(this);
    throw new IllegalStateException(localStringBuilder.toString());
  }
  
  public int getType()
  {
    if ((this.mType == -1) && (Build.VERSION.SDK_INT >= 23)) {
      return getType((Icon)this.mObj1);
    }
    return this.mType;
  }
  
  @NonNull
  public Uri getUri()
  {
    if ((this.mType == -1) && (Build.VERSION.SDK_INT >= 23)) {
      return getUri((Icon)this.mObj1);
    }
    return Uri.parse((String)this.mObj1);
  }
  
  public Drawable loadDrawable(Context paramContext)
  {
    checkResource(paramContext);
    if (Build.VERSION.SDK_INT >= 23) {
      return toIcon().loadDrawable(paramContext);
    }
    paramContext = loadDrawableInner(paramContext);
    if ((paramContext != null) && ((this.mTintList != null) || (this.mTintMode != DEFAULT_TINT_MODE)))
    {
      paramContext.mutate();
      DrawableCompat.setTintList(paramContext, this.mTintList);
      DrawableCompat.setTintMode(paramContext, this.mTintMode);
    }
    return paramContext;
  }
  
  public void onPostParceling()
  {
    this.mTintMode = PorterDuff.Mode.valueOf(this.mTintModeStr);
    int i = this.mType;
    if (i != -1)
    {
      switch (i)
      {
      default: 
        return;
      case 3: 
        this.mObj1 = this.mData;
        return;
      case 2: 
      case 4: 
        this.mObj1 = new String(this.mData, Charset.forName("UTF-16"));
        return;
      }
      if (this.mParcelable != null)
      {
        this.mObj1 = this.mParcelable;
        return;
      }
      this.mObj1 = this.mData;
      this.mType = 3;
      this.mInt1 = 0;
      this.mInt2 = this.mData.length;
      return;
    }
    if (this.mParcelable != null)
    {
      this.mObj1 = this.mParcelable;
      return;
    }
    throw new IllegalArgumentException("Invalid icon");
  }
  
  public void onPreParceling(boolean paramBoolean)
  {
    this.mTintModeStr = this.mTintMode.name();
    int i = this.mType;
    if (i != -1)
    {
      switch (i)
      {
      default: 
        return;
      case 4: 
        this.mData = this.mObj1.toString().getBytes(Charset.forName("UTF-16"));
        return;
      case 3: 
        this.mData = ((byte[])this.mObj1);
        return;
      case 2: 
        this.mData = ((String)this.mObj1).getBytes(Charset.forName("UTF-16"));
        return;
      }
      if (paramBoolean)
      {
        Bitmap localBitmap = (Bitmap)this.mObj1;
        ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
        localBitmap.compress(Bitmap.CompressFormat.PNG, 90, localByteArrayOutputStream);
        this.mData = localByteArrayOutputStream.toByteArray();
        return;
      }
      this.mParcelable = ((Parcelable)this.mObj1);
      return;
    }
    if (!paramBoolean)
    {
      this.mParcelable = ((Parcelable)this.mObj1);
      return;
    }
    throw new IllegalArgumentException("Can't serialize Icon created with IconCompat#createFromIcon");
  }
  
  public IconCompat setTint(@ColorInt int paramInt)
  {
    return setTintList(ColorStateList.valueOf(paramInt));
  }
  
  public IconCompat setTintList(ColorStateList paramColorStateList)
  {
    this.mTintList = paramColorStateList;
    return this;
  }
  
  public IconCompat setTintMode(PorterDuff.Mode paramMode)
  {
    this.mTintMode = paramMode;
    return this;
  }
  
  public Bundle toBundle()
  {
    Bundle localBundle = new Bundle();
    int i = this.mType;
    if (i != -1) {
      switch (i)
      {
      default: 
        throw new IllegalArgumentException("Invalid icon");
      case 3: 
        localBundle.putByteArray("obj", (byte[])this.mObj1);
        break;
      case 2: 
      case 4: 
        localBundle.putString("obj", (String)this.mObj1);
        break;
      case 1: 
      case 5: 
        localBundle.putParcelable("obj", (Bitmap)this.mObj1);
        break;
      }
    } else {
      localBundle.putParcelable("obj", (Parcelable)this.mObj1);
    }
    localBundle.putInt("type", this.mType);
    localBundle.putInt("int1", this.mInt1);
    localBundle.putInt("int2", this.mInt2);
    if (this.mTintList != null) {
      localBundle.putParcelable("tint_list", this.mTintList);
    }
    if (this.mTintMode != DEFAULT_TINT_MODE) {
      localBundle.putString("tint_mode", this.mTintMode.name());
    }
    return localBundle;
  }
  
  @RequiresApi(23)
  public Icon toIcon()
  {
    int i = this.mType;
    if (i != -1)
    {
      Icon localIcon;
      switch (i)
      {
      default: 
        throw new IllegalArgumentException("Unknown type");
      case 5: 
        if (Build.VERSION.SDK_INT >= 26) {
          localIcon = Icon.createWithAdaptiveBitmap((Bitmap)this.mObj1);
        } else {
          localIcon = Icon.createWithBitmap(createLegacyIconFromAdaptiveIcon((Bitmap)this.mObj1, false));
        }
        break;
      case 4: 
        localIcon = Icon.createWithContentUri((String)this.mObj1);
        break;
      case 3: 
        localIcon = Icon.createWithData((byte[])this.mObj1, this.mInt1, this.mInt2);
        break;
      case 2: 
        localIcon = Icon.createWithResource(getResPackage(), this.mInt1);
        break;
      case 1: 
        localIcon = Icon.createWithBitmap((Bitmap)this.mObj1);
      }
      if (this.mTintList != null) {
        localIcon.setTintList(this.mTintList);
      }
      if (this.mTintMode != DEFAULT_TINT_MODE) {
        localIcon.setTintMode(this.mTintMode);
      }
      return localIcon;
    }
    return (Icon)this.mObj1;
  }
  
  public String toString()
  {
    if (this.mType == -1) {
      return String.valueOf(this.mObj1);
    }
    StringBuilder localStringBuilder = new StringBuilder("Icon(typ=");
    localStringBuilder.append(typeToString(this.mType));
    switch (this.mType)
    {
    default: 
      break;
    case 4: 
      localStringBuilder.append(" uri=");
      localStringBuilder.append(this.mObj1);
      break;
    case 3: 
      localStringBuilder.append(" len=");
      localStringBuilder.append(this.mInt1);
      if (this.mInt2 != 0)
      {
        localStringBuilder.append(" off=");
        localStringBuilder.append(this.mInt2);
      }
      break;
    case 2: 
      localStringBuilder.append(" pkg=");
      localStringBuilder.append(getResPackage());
      localStringBuilder.append(" id=");
      localStringBuilder.append(String.format("0x%08x", new Object[] { Integer.valueOf(getResId()) }));
      break;
    case 1: 
    case 5: 
      localStringBuilder.append(" size=");
      localStringBuilder.append(((Bitmap)this.mObj1).getWidth());
      localStringBuilder.append("x");
      localStringBuilder.append(((Bitmap)this.mObj1).getHeight());
    }
    if (this.mTintList != null)
    {
      localStringBuilder.append(" tint=");
      localStringBuilder.append(this.mTintList);
    }
    if (this.mTintMode != DEFAULT_TINT_MODE)
    {
      localStringBuilder.append(" mode=");
      localStringBuilder.append(this.mTintMode);
    }
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public static @interface IconType {}
}


/* Location:              C:\Users\lixia\Desktop\android反编译三件套\dex2jar-2.0\classes-dex2jar.jar!\android\support\v4\graphics\drawable\IconCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */