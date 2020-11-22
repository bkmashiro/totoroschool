package android.support.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.VectorDrawable;
import android.os.Build.VERSION;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.content.res.ComplexColorCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.graphics.PathParser;
import android.support.v4.graphics.PathParser.PathDataNode;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.util.ArrayMap;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class VectorDrawableCompat
  extends VectorDrawableCommon
{
  private static final boolean DBG_VECTOR_DRAWABLE = false;
  static final PorterDuff.Mode DEFAULT_TINT_MODE = PorterDuff.Mode.SRC_IN;
  private static final int LINECAP_BUTT = 0;
  private static final int LINECAP_ROUND = 1;
  private static final int LINECAP_SQUARE = 2;
  private static final int LINEJOIN_BEVEL = 2;
  private static final int LINEJOIN_MITER = 0;
  private static final int LINEJOIN_ROUND = 1;
  static final String LOGTAG = "VectorDrawableCompat";
  private static final int MAX_CACHED_BITMAP_SIZE = 2048;
  private static final String SHAPE_CLIP_PATH = "clip-path";
  private static final String SHAPE_GROUP = "group";
  private static final String SHAPE_PATH = "path";
  private static final String SHAPE_VECTOR = "vector";
  private boolean mAllowCaching = true;
  private Drawable.ConstantState mCachedConstantStateDelegate;
  private ColorFilter mColorFilter;
  private boolean mMutated;
  private PorterDuffColorFilter mTintFilter;
  private final Rect mTmpBounds = new Rect();
  private final float[] mTmpFloats = new float[9];
  private final Matrix mTmpMatrix = new Matrix();
  private VectorDrawableCompatState mVectorState;
  
  VectorDrawableCompat()
  {
    this.mVectorState = new VectorDrawableCompatState();
  }
  
  VectorDrawableCompat(@NonNull VectorDrawableCompatState paramVectorDrawableCompatState)
  {
    this.mVectorState = paramVectorDrawableCompatState;
    this.mTintFilter = updateTintFilter(this.mTintFilter, paramVectorDrawableCompatState.mTint, paramVectorDrawableCompatState.mTintMode);
  }
  
  static int applyAlpha(int paramInt, float paramFloat)
  {
    return paramInt & 0xFFFFFF | (int)(Color.alpha(paramInt) * paramFloat) << 24;
  }
  
  @Nullable
  public static VectorDrawableCompat create(@NonNull Resources paramResources, @DrawableRes int paramInt, @Nullable Resources.Theme paramTheme)
  {
    Object localObject;
    if (Build.VERSION.SDK_INT >= 24)
    {
      localObject = new VectorDrawableCompat();
      ((VectorDrawableCompat)localObject).mDelegateDrawable = ResourcesCompat.getDrawable(paramResources, paramInt, paramTheme);
      ((VectorDrawableCompat)localObject).mCachedConstantStateDelegate = new VectorDrawableDelegateState(((VectorDrawableCompat)localObject).mDelegateDrawable.getConstantState());
      return (VectorDrawableCompat)localObject;
    }
    try
    {
      localObject = paramResources.getXml(paramInt);
      AttributeSet localAttributeSet = Xml.asAttributeSet((XmlPullParser)localObject);
      do
      {
        paramInt = ((XmlPullParser)localObject).next();
      } while ((paramInt != 2) && (paramInt != 1));
      if (paramInt == 2) {
        return createFromXmlInner(paramResources, (XmlPullParser)localObject, localAttributeSet, paramTheme);
      }
      throw new XmlPullParserException("No start tag found");
    }
    catch (IOException paramResources)
    {
      Log.e("VectorDrawableCompat", "parser error", paramResources);
    }
    catch (XmlPullParserException paramResources)
    {
      Log.e("VectorDrawableCompat", "parser error", paramResources);
    }
    return null;
  }
  
  public static VectorDrawableCompat createFromXmlInner(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme)
    throws XmlPullParserException, IOException
  {
    VectorDrawableCompat localVectorDrawableCompat = new VectorDrawableCompat();
    localVectorDrawableCompat.inflate(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    return localVectorDrawableCompat;
  }
  
  private void inflateInternal(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme)
    throws XmlPullParserException, IOException
  {
    VectorDrawableCompatState localVectorDrawableCompatState = this.mVectorState;
    VPathRenderer localVPathRenderer = localVectorDrawableCompatState.mVPathRenderer;
    ArrayDeque localArrayDeque = new ArrayDeque();
    localArrayDeque.push(localVPathRenderer.mRootGroup);
    int k = paramXmlPullParser.getEventType();
    int m = paramXmlPullParser.getDepth();
    int j;
    for (int i = 1; (k != 1) && ((paramXmlPullParser.getDepth() >= m + 1) || (k != 3)); i = j)
    {
      if (k == 2)
      {
        Object localObject = paramXmlPullParser.getName();
        VGroup localVGroup = (VGroup)localArrayDeque.peek();
        if ("path".equals(localObject))
        {
          localObject = new VFullPath();
          ((VFullPath)localObject).inflate(paramResources, paramAttributeSet, paramTheme, paramXmlPullParser);
          localVGroup.mChildren.add(localObject);
          if (((VFullPath)localObject).getPathName() != null) {
            localVPathRenderer.mVGTargetsMap.put(((VFullPath)localObject).getPathName(), localObject);
          }
          j = 0;
          i = localVectorDrawableCompatState.mChangingConfigurations;
          localVectorDrawableCompatState.mChangingConfigurations = (((VFullPath)localObject).mChangingConfigurations | i);
        }
        else if ("clip-path".equals(localObject))
        {
          localObject = new VClipPath();
          ((VClipPath)localObject).inflate(paramResources, paramAttributeSet, paramTheme, paramXmlPullParser);
          localVGroup.mChildren.add(localObject);
          if (((VClipPath)localObject).getPathName() != null) {
            localVPathRenderer.mVGTargetsMap.put(((VClipPath)localObject).getPathName(), localObject);
          }
          j = localVectorDrawableCompatState.mChangingConfigurations;
          localVectorDrawableCompatState.mChangingConfigurations = (((VClipPath)localObject).mChangingConfigurations | j);
          j = i;
        }
        else
        {
          j = i;
          if ("group".equals(localObject))
          {
            localObject = new VGroup();
            ((VGroup)localObject).inflate(paramResources, paramAttributeSet, paramTheme, paramXmlPullParser);
            localVGroup.mChildren.add(localObject);
            localArrayDeque.push(localObject);
            if (((VGroup)localObject).getGroupName() != null) {
              localVPathRenderer.mVGTargetsMap.put(((VGroup)localObject).getGroupName(), localObject);
            }
            j = localVectorDrawableCompatState.mChangingConfigurations;
            localVectorDrawableCompatState.mChangingConfigurations = (((VGroup)localObject).mChangingConfigurations | j);
            j = i;
          }
        }
      }
      else
      {
        j = i;
        if (k == 3)
        {
          j = i;
          if ("group".equals(paramXmlPullParser.getName()))
          {
            localArrayDeque.pop();
            j = i;
          }
        }
      }
      k = paramXmlPullParser.next();
    }
    if (i == 0) {
      return;
    }
    throw new XmlPullParserException("no path defined");
  }
  
  private boolean needMirroring()
  {
    int i = Build.VERSION.SDK_INT;
    boolean bool2 = false;
    if (i >= 17)
    {
      boolean bool1 = bool2;
      if (isAutoMirrored())
      {
        bool1 = bool2;
        if (DrawableCompat.getLayoutDirection(this) == 1) {
          bool1 = true;
        }
      }
      return bool1;
    }
    return false;
  }
  
  private static PorterDuff.Mode parseTintModeCompat(int paramInt, PorterDuff.Mode paramMode)
  {
    if (paramInt != 3)
    {
      if (paramInt != 5)
      {
        if (paramInt != 9)
        {
          switch (paramInt)
          {
          default: 
            return paramMode;
          case 16: 
            return PorterDuff.Mode.ADD;
          case 15: 
            return PorterDuff.Mode.SCREEN;
          }
          return PorterDuff.Mode.MULTIPLY;
        }
        return PorterDuff.Mode.SRC_ATOP;
      }
      return PorterDuff.Mode.SRC_IN;
    }
    return PorterDuff.Mode.SRC_OVER;
  }
  
  private void printGroupTree(VGroup paramVGroup, int paramInt)
  {
    int j = 0;
    Object localObject = "";
    int i = 0;
    while (i < paramInt)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append((String)localObject);
      localStringBuilder.append("    ");
      localObject = localStringBuilder.toString();
      i += 1;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append((String)localObject);
    localStringBuilder.append("current group is :");
    localStringBuilder.append(paramVGroup.getGroupName());
    localStringBuilder.append(" rotation is ");
    localStringBuilder.append(paramVGroup.mRotate);
    Log.v("VectorDrawableCompat", localStringBuilder.toString());
    localStringBuilder = new StringBuilder();
    localStringBuilder.append((String)localObject);
    localStringBuilder.append("matrix is :");
    localStringBuilder.append(paramVGroup.getLocalMatrix().toString());
    Log.v("VectorDrawableCompat", localStringBuilder.toString());
    i = j;
    while (i < paramVGroup.mChildren.size())
    {
      localObject = (VObject)paramVGroup.mChildren.get(i);
      if ((localObject instanceof VGroup)) {
        printGroupTree((VGroup)localObject, paramInt + 1);
      } else {
        ((VPath)localObject).printVPath(paramInt + 1);
      }
      i += 1;
    }
  }
  
  private void updateStateFromTypedArray(TypedArray paramTypedArray, XmlPullParser paramXmlPullParser)
    throws XmlPullParserException
  {
    VectorDrawableCompatState localVectorDrawableCompatState = this.mVectorState;
    VPathRenderer localVPathRenderer = localVectorDrawableCompatState.mVPathRenderer;
    localVectorDrawableCompatState.mTintMode = parseTintModeCompat(TypedArrayUtils.getNamedInt(paramTypedArray, paramXmlPullParser, "tintMode", 6, -1), PorterDuff.Mode.SRC_IN);
    ColorStateList localColorStateList = paramTypedArray.getColorStateList(1);
    if (localColorStateList != null) {
      localVectorDrawableCompatState.mTint = localColorStateList;
    }
    localVectorDrawableCompatState.mAutoMirrored = TypedArrayUtils.getNamedBoolean(paramTypedArray, paramXmlPullParser, "autoMirrored", 5, localVectorDrawableCompatState.mAutoMirrored);
    localVPathRenderer.mViewportWidth = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "viewportWidth", 7, localVPathRenderer.mViewportWidth);
    localVPathRenderer.mViewportHeight = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "viewportHeight", 8, localVPathRenderer.mViewportHeight);
    if (localVPathRenderer.mViewportWidth > 0.0F)
    {
      if (localVPathRenderer.mViewportHeight > 0.0F)
      {
        localVPathRenderer.mBaseWidth = paramTypedArray.getDimension(3, localVPathRenderer.mBaseWidth);
        localVPathRenderer.mBaseHeight = paramTypedArray.getDimension(2, localVPathRenderer.mBaseHeight);
        if (localVPathRenderer.mBaseWidth > 0.0F)
        {
          if (localVPathRenderer.mBaseHeight > 0.0F)
          {
            localVPathRenderer.setAlpha(TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "alpha", 4, localVPathRenderer.getAlpha()));
            paramTypedArray = paramTypedArray.getString(0);
            if (paramTypedArray != null)
            {
              localVPathRenderer.mRootName = paramTypedArray;
              localVPathRenderer.mVGTargetsMap.put(paramTypedArray, localVPathRenderer);
            }
            return;
          }
          paramXmlPullParser = new StringBuilder();
          paramXmlPullParser.append(paramTypedArray.getPositionDescription());
          paramXmlPullParser.append("<vector> tag requires height > 0");
          throw new XmlPullParserException(paramXmlPullParser.toString());
        }
        paramXmlPullParser = new StringBuilder();
        paramXmlPullParser.append(paramTypedArray.getPositionDescription());
        paramXmlPullParser.append("<vector> tag requires width > 0");
        throw new XmlPullParserException(paramXmlPullParser.toString());
      }
      paramXmlPullParser = new StringBuilder();
      paramXmlPullParser.append(paramTypedArray.getPositionDescription());
      paramXmlPullParser.append("<vector> tag requires viewportHeight > 0");
      throw new XmlPullParserException(paramXmlPullParser.toString());
    }
    paramXmlPullParser = new StringBuilder();
    paramXmlPullParser.append(paramTypedArray.getPositionDescription());
    paramXmlPullParser.append("<vector> tag requires viewportWidth > 0");
    throw new XmlPullParserException(paramXmlPullParser.toString());
  }
  
  public boolean canApplyTheme()
  {
    if (this.mDelegateDrawable != null) {
      DrawableCompat.canApplyTheme(this.mDelegateDrawable);
    }
    return false;
  }
  
  public void draw(Canvas paramCanvas)
  {
    if (this.mDelegateDrawable != null)
    {
      this.mDelegateDrawable.draw(paramCanvas);
      return;
    }
    copyBounds(this.mTmpBounds);
    if (this.mTmpBounds.width() > 0)
    {
      if (this.mTmpBounds.height() <= 0) {
        return;
      }
      Object localObject;
      if (this.mColorFilter == null) {
        localObject = this.mTintFilter;
      } else {
        localObject = this.mColorFilter;
      }
      paramCanvas.getMatrix(this.mTmpMatrix);
      this.mTmpMatrix.getValues(this.mTmpFloats);
      float f1 = Math.abs(this.mTmpFloats[0]);
      float f2 = Math.abs(this.mTmpFloats[4]);
      float f4 = Math.abs(this.mTmpFloats[1]);
      float f3 = Math.abs(this.mTmpFloats[3]);
      if ((f4 != 0.0F) || (f3 != 0.0F))
      {
        f1 = 1.0F;
        f2 = 1.0F;
      }
      int i = (int)(this.mTmpBounds.width() * f1);
      int j = (int)(this.mTmpBounds.height() * f2);
      i = Math.min(2048, i);
      j = Math.min(2048, j);
      if (i > 0)
      {
        if (j <= 0) {
          return;
        }
        int k = paramCanvas.save();
        paramCanvas.translate(this.mTmpBounds.left, this.mTmpBounds.top);
        if (needMirroring())
        {
          paramCanvas.translate(this.mTmpBounds.width(), 0.0F);
          paramCanvas.scale(-1.0F, 1.0F);
        }
        this.mTmpBounds.offsetTo(0, 0);
        this.mVectorState.createCachedBitmapIfNeeded(i, j);
        if (!this.mAllowCaching)
        {
          this.mVectorState.updateCachedBitmap(i, j);
        }
        else if (!this.mVectorState.canReuseCache())
        {
          this.mVectorState.updateCachedBitmap(i, j);
          this.mVectorState.updateCacheStates();
        }
        this.mVectorState.drawCachedBitmapWithRootAlpha(paramCanvas, (ColorFilter)localObject, this.mTmpBounds);
        paramCanvas.restoreToCount(k);
        return;
      }
      return;
    }
  }
  
  public int getAlpha()
  {
    if (this.mDelegateDrawable != null) {
      return DrawableCompat.getAlpha(this.mDelegateDrawable);
    }
    return this.mVectorState.mVPathRenderer.getRootAlpha();
  }
  
  public int getChangingConfigurations()
  {
    if (this.mDelegateDrawable != null) {
      return this.mDelegateDrawable.getChangingConfigurations();
    }
    return super.getChangingConfigurations() | this.mVectorState.getChangingConfigurations();
  }
  
  public Drawable.ConstantState getConstantState()
  {
    if ((this.mDelegateDrawable != null) && (Build.VERSION.SDK_INT >= 24)) {
      return new VectorDrawableDelegateState(this.mDelegateDrawable.getConstantState());
    }
    this.mVectorState.mChangingConfigurations = getChangingConfigurations();
    return this.mVectorState;
  }
  
  public int getIntrinsicHeight()
  {
    if (this.mDelegateDrawable != null) {
      return this.mDelegateDrawable.getIntrinsicHeight();
    }
    return (int)this.mVectorState.mVPathRenderer.mBaseHeight;
  }
  
  public int getIntrinsicWidth()
  {
    if (this.mDelegateDrawable != null) {
      return this.mDelegateDrawable.getIntrinsicWidth();
    }
    return (int)this.mVectorState.mVPathRenderer.mBaseWidth;
  }
  
  public int getOpacity()
  {
    if (this.mDelegateDrawable != null) {
      return this.mDelegateDrawable.getOpacity();
    }
    return -3;
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public float getPixelSize()
  {
    if ((this.mVectorState != null) && (this.mVectorState.mVPathRenderer != null) && (this.mVectorState.mVPathRenderer.mBaseWidth != 0.0F) && (this.mVectorState.mVPathRenderer.mBaseHeight != 0.0F) && (this.mVectorState.mVPathRenderer.mViewportHeight != 0.0F) && (this.mVectorState.mVPathRenderer.mViewportWidth != 0.0F))
    {
      float f1 = this.mVectorState.mVPathRenderer.mBaseWidth;
      float f2 = this.mVectorState.mVPathRenderer.mBaseHeight;
      float f3 = this.mVectorState.mVPathRenderer.mViewportWidth;
      float f4 = this.mVectorState.mVPathRenderer.mViewportHeight;
      return Math.min(f3 / f1, f4 / f2);
    }
    return 1.0F;
  }
  
  Object getTargetByName(String paramString)
  {
    return this.mVectorState.mVPathRenderer.mVGTargetsMap.get(paramString);
  }
  
  public void inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet)
    throws XmlPullParserException, IOException
  {
    if (this.mDelegateDrawable != null)
    {
      this.mDelegateDrawable.inflate(paramResources, paramXmlPullParser, paramAttributeSet);
      return;
    }
    inflate(paramResources, paramXmlPullParser, paramAttributeSet, null);
  }
  
  public void inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme)
    throws XmlPullParserException, IOException
  {
    if (this.mDelegateDrawable != null)
    {
      DrawableCompat.inflate(this.mDelegateDrawable, paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
      return;
    }
    VectorDrawableCompatState localVectorDrawableCompatState = this.mVectorState;
    localVectorDrawableCompatState.mVPathRenderer = new VPathRenderer();
    TypedArray localTypedArray = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.STYLEABLE_VECTOR_DRAWABLE_TYPE_ARRAY);
    updateStateFromTypedArray(localTypedArray, paramXmlPullParser);
    localTypedArray.recycle();
    localVectorDrawableCompatState.mChangingConfigurations = getChangingConfigurations();
    localVectorDrawableCompatState.mCacheDirty = true;
    inflateInternal(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    this.mTintFilter = updateTintFilter(this.mTintFilter, localVectorDrawableCompatState.mTint, localVectorDrawableCompatState.mTintMode);
  }
  
  public void invalidateSelf()
  {
    if (this.mDelegateDrawable != null)
    {
      this.mDelegateDrawable.invalidateSelf();
      return;
    }
    super.invalidateSelf();
  }
  
  public boolean isAutoMirrored()
  {
    if (this.mDelegateDrawable != null) {
      return DrawableCompat.isAutoMirrored(this.mDelegateDrawable);
    }
    return this.mVectorState.mAutoMirrored;
  }
  
  public boolean isStateful()
  {
    if (this.mDelegateDrawable != null) {
      return this.mDelegateDrawable.isStateful();
    }
    return (super.isStateful()) || ((this.mVectorState != null) && ((this.mVectorState.isStateful()) || ((this.mVectorState.mTint != null) && (this.mVectorState.mTint.isStateful()))));
  }
  
  public Drawable mutate()
  {
    if (this.mDelegateDrawable != null)
    {
      this.mDelegateDrawable.mutate();
      return this;
    }
    if ((!this.mMutated) && (super.mutate() == this))
    {
      this.mVectorState = new VectorDrawableCompatState(this.mVectorState);
      this.mMutated = true;
    }
    return this;
  }
  
  protected void onBoundsChange(Rect paramRect)
  {
    if (this.mDelegateDrawable != null) {
      this.mDelegateDrawable.setBounds(paramRect);
    }
  }
  
  protected boolean onStateChange(int[] paramArrayOfInt)
  {
    if (this.mDelegateDrawable != null) {
      return this.mDelegateDrawable.setState(paramArrayOfInt);
    }
    boolean bool2 = false;
    VectorDrawableCompatState localVectorDrawableCompatState = this.mVectorState;
    boolean bool1 = bool2;
    if (localVectorDrawableCompatState.mTint != null)
    {
      bool1 = bool2;
      if (localVectorDrawableCompatState.mTintMode != null)
      {
        this.mTintFilter = updateTintFilter(this.mTintFilter, localVectorDrawableCompatState.mTint, localVectorDrawableCompatState.mTintMode);
        invalidateSelf();
        bool1 = true;
      }
    }
    bool2 = bool1;
    if (localVectorDrawableCompatState.isStateful())
    {
      bool2 = bool1;
      if (localVectorDrawableCompatState.onStateChanged(paramArrayOfInt))
      {
        invalidateSelf();
        bool2 = true;
      }
    }
    return bool2;
  }
  
  public void scheduleSelf(Runnable paramRunnable, long paramLong)
  {
    if (this.mDelegateDrawable != null)
    {
      this.mDelegateDrawable.scheduleSelf(paramRunnable, paramLong);
      return;
    }
    super.scheduleSelf(paramRunnable, paramLong);
  }
  
  void setAllowCaching(boolean paramBoolean)
  {
    this.mAllowCaching = paramBoolean;
  }
  
  public void setAlpha(int paramInt)
  {
    if (this.mDelegateDrawable != null)
    {
      this.mDelegateDrawable.setAlpha(paramInt);
      return;
    }
    if (this.mVectorState.mVPathRenderer.getRootAlpha() != paramInt)
    {
      this.mVectorState.mVPathRenderer.setRootAlpha(paramInt);
      invalidateSelf();
    }
  }
  
  public void setAutoMirrored(boolean paramBoolean)
  {
    if (this.mDelegateDrawable != null)
    {
      DrawableCompat.setAutoMirrored(this.mDelegateDrawable, paramBoolean);
      return;
    }
    this.mVectorState.mAutoMirrored = paramBoolean;
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    if (this.mDelegateDrawable != null)
    {
      this.mDelegateDrawable.setColorFilter(paramColorFilter);
      return;
    }
    this.mColorFilter = paramColorFilter;
    invalidateSelf();
  }
  
  public void setTint(int paramInt)
  {
    if (this.mDelegateDrawable != null)
    {
      DrawableCompat.setTint(this.mDelegateDrawable, paramInt);
      return;
    }
    setTintList(ColorStateList.valueOf(paramInt));
  }
  
  public void setTintList(ColorStateList paramColorStateList)
  {
    if (this.mDelegateDrawable != null)
    {
      DrawableCompat.setTintList(this.mDelegateDrawable, paramColorStateList);
      return;
    }
    VectorDrawableCompatState localVectorDrawableCompatState = this.mVectorState;
    if (localVectorDrawableCompatState.mTint != paramColorStateList)
    {
      localVectorDrawableCompatState.mTint = paramColorStateList;
      this.mTintFilter = updateTintFilter(this.mTintFilter, paramColorStateList, localVectorDrawableCompatState.mTintMode);
      invalidateSelf();
    }
  }
  
  public void setTintMode(PorterDuff.Mode paramMode)
  {
    if (this.mDelegateDrawable != null)
    {
      DrawableCompat.setTintMode(this.mDelegateDrawable, paramMode);
      return;
    }
    VectorDrawableCompatState localVectorDrawableCompatState = this.mVectorState;
    if (localVectorDrawableCompatState.mTintMode != paramMode)
    {
      localVectorDrawableCompatState.mTintMode = paramMode;
      this.mTintFilter = updateTintFilter(this.mTintFilter, localVectorDrawableCompatState.mTint, paramMode);
      invalidateSelf();
    }
  }
  
  public boolean setVisible(boolean paramBoolean1, boolean paramBoolean2)
  {
    if (this.mDelegateDrawable != null) {
      return this.mDelegateDrawable.setVisible(paramBoolean1, paramBoolean2);
    }
    return super.setVisible(paramBoolean1, paramBoolean2);
  }
  
  public void unscheduleSelf(Runnable paramRunnable)
  {
    if (this.mDelegateDrawable != null)
    {
      this.mDelegateDrawable.unscheduleSelf(paramRunnable);
      return;
    }
    super.unscheduleSelf(paramRunnable);
  }
  
  PorterDuffColorFilter updateTintFilter(PorterDuffColorFilter paramPorterDuffColorFilter, ColorStateList paramColorStateList, PorterDuff.Mode paramMode)
  {
    if ((paramColorStateList != null) && (paramMode != null)) {
      return new PorterDuffColorFilter(paramColorStateList.getColorForState(getState(), 0), paramMode);
    }
    return null;
  }
  
  private static class VClipPath
    extends VectorDrawableCompat.VPath
  {
    public VClipPath() {}
    
    public VClipPath(VClipPath paramVClipPath)
    {
      super();
    }
    
    private void updateStateFromTypedArray(TypedArray paramTypedArray)
    {
      String str = paramTypedArray.getString(0);
      if (str != null) {
        this.mPathName = str;
      }
      paramTypedArray = paramTypedArray.getString(1);
      if (paramTypedArray != null) {
        this.mNodes = PathParser.createNodesFromPathData(paramTypedArray);
      }
    }
    
    public void inflate(Resources paramResources, AttributeSet paramAttributeSet, Resources.Theme paramTheme, XmlPullParser paramXmlPullParser)
    {
      if (!TypedArrayUtils.hasAttribute(paramXmlPullParser, "pathData")) {
        return;
      }
      paramResources = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.STYLEABLE_VECTOR_DRAWABLE_CLIP_PATH);
      updateStateFromTypedArray(paramResources);
      paramResources.recycle();
    }
    
    public boolean isClipPath()
    {
      return true;
    }
  }
  
  private static class VFullPath
    extends VectorDrawableCompat.VPath
  {
    private static final int FILL_TYPE_WINDING = 0;
    float mFillAlpha = 1.0F;
    ComplexColorCompat mFillColor;
    int mFillRule = 0;
    float mStrokeAlpha = 1.0F;
    ComplexColorCompat mStrokeColor;
    Paint.Cap mStrokeLineCap = Paint.Cap.BUTT;
    Paint.Join mStrokeLineJoin = Paint.Join.MITER;
    float mStrokeMiterlimit = 4.0F;
    float mStrokeWidth = 0.0F;
    private int[] mThemeAttrs;
    float mTrimPathEnd = 1.0F;
    float mTrimPathOffset = 0.0F;
    float mTrimPathStart = 0.0F;
    
    public VFullPath() {}
    
    public VFullPath(VFullPath paramVFullPath)
    {
      super();
      this.mThemeAttrs = paramVFullPath.mThemeAttrs;
      this.mStrokeColor = paramVFullPath.mStrokeColor;
      this.mStrokeWidth = paramVFullPath.mStrokeWidth;
      this.mStrokeAlpha = paramVFullPath.mStrokeAlpha;
      this.mFillColor = paramVFullPath.mFillColor;
      this.mFillRule = paramVFullPath.mFillRule;
      this.mFillAlpha = paramVFullPath.mFillAlpha;
      this.mTrimPathStart = paramVFullPath.mTrimPathStart;
      this.mTrimPathEnd = paramVFullPath.mTrimPathEnd;
      this.mTrimPathOffset = paramVFullPath.mTrimPathOffset;
      this.mStrokeLineCap = paramVFullPath.mStrokeLineCap;
      this.mStrokeLineJoin = paramVFullPath.mStrokeLineJoin;
      this.mStrokeMiterlimit = paramVFullPath.mStrokeMiterlimit;
    }
    
    private Paint.Cap getStrokeLineCap(int paramInt, Paint.Cap paramCap)
    {
      switch (paramInt)
      {
      default: 
        return paramCap;
      case 2: 
        return Paint.Cap.SQUARE;
      case 1: 
        return Paint.Cap.ROUND;
      }
      return Paint.Cap.BUTT;
    }
    
    private Paint.Join getStrokeLineJoin(int paramInt, Paint.Join paramJoin)
    {
      switch (paramInt)
      {
      default: 
        return paramJoin;
      case 2: 
        return Paint.Join.BEVEL;
      case 1: 
        return Paint.Join.ROUND;
      }
      return Paint.Join.MITER;
    }
    
    private void updateStateFromTypedArray(TypedArray paramTypedArray, XmlPullParser paramXmlPullParser, Resources.Theme paramTheme)
    {
      this.mThemeAttrs = null;
      if (!TypedArrayUtils.hasAttribute(paramXmlPullParser, "pathData")) {
        return;
      }
      String str = paramTypedArray.getString(0);
      if (str != null) {
        this.mPathName = str;
      }
      str = paramTypedArray.getString(2);
      if (str != null) {
        this.mNodes = PathParser.createNodesFromPathData(str);
      }
      this.mFillColor = TypedArrayUtils.getNamedComplexColor(paramTypedArray, paramXmlPullParser, paramTheme, "fillColor", 1, 0);
      this.mFillAlpha = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "fillAlpha", 12, this.mFillAlpha);
      this.mStrokeLineCap = getStrokeLineCap(TypedArrayUtils.getNamedInt(paramTypedArray, paramXmlPullParser, "strokeLineCap", 8, -1), this.mStrokeLineCap);
      this.mStrokeLineJoin = getStrokeLineJoin(TypedArrayUtils.getNamedInt(paramTypedArray, paramXmlPullParser, "strokeLineJoin", 9, -1), this.mStrokeLineJoin);
      this.mStrokeMiterlimit = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "strokeMiterLimit", 10, this.mStrokeMiterlimit);
      this.mStrokeColor = TypedArrayUtils.getNamedComplexColor(paramTypedArray, paramXmlPullParser, paramTheme, "strokeColor", 3, 0);
      this.mStrokeAlpha = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "strokeAlpha", 11, this.mStrokeAlpha);
      this.mStrokeWidth = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "strokeWidth", 4, this.mStrokeWidth);
      this.mTrimPathEnd = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "trimPathEnd", 6, this.mTrimPathEnd);
      this.mTrimPathOffset = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "trimPathOffset", 7, this.mTrimPathOffset);
      this.mTrimPathStart = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "trimPathStart", 5, this.mTrimPathStart);
      this.mFillRule = TypedArrayUtils.getNamedInt(paramTypedArray, paramXmlPullParser, "fillType", 13, this.mFillRule);
    }
    
    public void applyTheme(Resources.Theme paramTheme)
    {
      if (this.mThemeAttrs == null) {}
    }
    
    public boolean canApplyTheme()
    {
      return this.mThemeAttrs != null;
    }
    
    float getFillAlpha()
    {
      return this.mFillAlpha;
    }
    
    @ColorInt
    int getFillColor()
    {
      return this.mFillColor.getColor();
    }
    
    float getStrokeAlpha()
    {
      return this.mStrokeAlpha;
    }
    
    @ColorInt
    int getStrokeColor()
    {
      return this.mStrokeColor.getColor();
    }
    
    float getStrokeWidth()
    {
      return this.mStrokeWidth;
    }
    
    float getTrimPathEnd()
    {
      return this.mTrimPathEnd;
    }
    
    float getTrimPathOffset()
    {
      return this.mTrimPathOffset;
    }
    
    float getTrimPathStart()
    {
      return this.mTrimPathStart;
    }
    
    public void inflate(Resources paramResources, AttributeSet paramAttributeSet, Resources.Theme paramTheme, XmlPullParser paramXmlPullParser)
    {
      paramResources = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.STYLEABLE_VECTOR_DRAWABLE_PATH);
      updateStateFromTypedArray(paramResources, paramXmlPullParser, paramTheme);
      paramResources.recycle();
    }
    
    public boolean isStateful()
    {
      return (this.mFillColor.isStateful()) || (this.mStrokeColor.isStateful());
    }
    
    public boolean onStateChanged(int[] paramArrayOfInt)
    {
      boolean bool = this.mFillColor.onStateChanged(paramArrayOfInt);
      return this.mStrokeColor.onStateChanged(paramArrayOfInt) | bool;
    }
    
    void setFillAlpha(float paramFloat)
    {
      this.mFillAlpha = paramFloat;
    }
    
    void setFillColor(int paramInt)
    {
      this.mFillColor.setColor(paramInt);
    }
    
    void setStrokeAlpha(float paramFloat)
    {
      this.mStrokeAlpha = paramFloat;
    }
    
    void setStrokeColor(int paramInt)
    {
      this.mStrokeColor.setColor(paramInt);
    }
    
    void setStrokeWidth(float paramFloat)
    {
      this.mStrokeWidth = paramFloat;
    }
    
    void setTrimPathEnd(float paramFloat)
    {
      this.mTrimPathEnd = paramFloat;
    }
    
    void setTrimPathOffset(float paramFloat)
    {
      this.mTrimPathOffset = paramFloat;
    }
    
    void setTrimPathStart(float paramFloat)
    {
      this.mTrimPathStart = paramFloat;
    }
  }
  
  private static class VGroup
    extends VectorDrawableCompat.VObject
  {
    int mChangingConfigurations;
    final ArrayList<VectorDrawableCompat.VObject> mChildren = new ArrayList();
    private String mGroupName = null;
    final Matrix mLocalMatrix = new Matrix();
    private float mPivotX = 0.0F;
    private float mPivotY = 0.0F;
    float mRotate = 0.0F;
    private float mScaleX = 1.0F;
    private float mScaleY = 1.0F;
    final Matrix mStackedMatrix = new Matrix();
    private int[] mThemeAttrs;
    private float mTranslateX = 0.0F;
    private float mTranslateY = 0.0F;
    
    public VGroup()
    {
      super();
    }
    
    public VGroup(VGroup paramVGroup, ArrayMap<String, Object> paramArrayMap)
    {
      super();
      this.mRotate = paramVGroup.mRotate;
      this.mPivotX = paramVGroup.mPivotX;
      this.mPivotY = paramVGroup.mPivotY;
      this.mScaleX = paramVGroup.mScaleX;
      this.mScaleY = paramVGroup.mScaleY;
      this.mTranslateX = paramVGroup.mTranslateX;
      this.mTranslateY = paramVGroup.mTranslateY;
      this.mThemeAttrs = paramVGroup.mThemeAttrs;
      this.mGroupName = paramVGroup.mGroupName;
      this.mChangingConfigurations = paramVGroup.mChangingConfigurations;
      if (this.mGroupName != null) {
        paramArrayMap.put(this.mGroupName, this);
      }
      this.mLocalMatrix.set(paramVGroup.mLocalMatrix);
      ArrayList localArrayList = paramVGroup.mChildren;
      int i = 0;
      while (i < localArrayList.size())
      {
        paramVGroup = localArrayList.get(i);
        if ((paramVGroup instanceof VGroup))
        {
          paramVGroup = (VGroup)paramVGroup;
          this.mChildren.add(new VGroup(paramVGroup, paramArrayMap));
        }
        else
        {
          if ((paramVGroup instanceof VectorDrawableCompat.VFullPath))
          {
            paramVGroup = new VectorDrawableCompat.VFullPath((VectorDrawableCompat.VFullPath)paramVGroup);
          }
          else
          {
            if (!(paramVGroup instanceof VectorDrawableCompat.VClipPath)) {
              break label316;
            }
            paramVGroup = new VectorDrawableCompat.VClipPath((VectorDrawableCompat.VClipPath)paramVGroup);
          }
          this.mChildren.add(paramVGroup);
          if (paramVGroup.mPathName != null) {
            paramArrayMap.put(paramVGroup.mPathName, paramVGroup);
          }
        }
        i += 1;
        continue;
        label316:
        throw new IllegalStateException("Unknown object in the tree!");
      }
    }
    
    private void updateLocalMatrix()
    {
      this.mLocalMatrix.reset();
      this.mLocalMatrix.postTranslate(-this.mPivotX, -this.mPivotY);
      this.mLocalMatrix.postScale(this.mScaleX, this.mScaleY);
      this.mLocalMatrix.postRotate(this.mRotate, 0.0F, 0.0F);
      this.mLocalMatrix.postTranslate(this.mTranslateX + this.mPivotX, this.mTranslateY + this.mPivotY);
    }
    
    private void updateStateFromTypedArray(TypedArray paramTypedArray, XmlPullParser paramXmlPullParser)
    {
      this.mThemeAttrs = null;
      this.mRotate = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "rotation", 5, this.mRotate);
      this.mPivotX = paramTypedArray.getFloat(1, this.mPivotX);
      this.mPivotY = paramTypedArray.getFloat(2, this.mPivotY);
      this.mScaleX = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "scaleX", 3, this.mScaleX);
      this.mScaleY = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "scaleY", 4, this.mScaleY);
      this.mTranslateX = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "translateX", 6, this.mTranslateX);
      this.mTranslateY = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "translateY", 7, this.mTranslateY);
      paramTypedArray = paramTypedArray.getString(0);
      if (paramTypedArray != null) {
        this.mGroupName = paramTypedArray;
      }
      updateLocalMatrix();
    }
    
    public String getGroupName()
    {
      return this.mGroupName;
    }
    
    public Matrix getLocalMatrix()
    {
      return this.mLocalMatrix;
    }
    
    public float getPivotX()
    {
      return this.mPivotX;
    }
    
    public float getPivotY()
    {
      return this.mPivotY;
    }
    
    public float getRotation()
    {
      return this.mRotate;
    }
    
    public float getScaleX()
    {
      return this.mScaleX;
    }
    
    public float getScaleY()
    {
      return this.mScaleY;
    }
    
    public float getTranslateX()
    {
      return this.mTranslateX;
    }
    
    public float getTranslateY()
    {
      return this.mTranslateY;
    }
    
    public void inflate(Resources paramResources, AttributeSet paramAttributeSet, Resources.Theme paramTheme, XmlPullParser paramXmlPullParser)
    {
      paramResources = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.STYLEABLE_VECTOR_DRAWABLE_GROUP);
      updateStateFromTypedArray(paramResources, paramXmlPullParser);
      paramResources.recycle();
    }
    
    public boolean isStateful()
    {
      int i = 0;
      while (i < this.mChildren.size())
      {
        if (((VectorDrawableCompat.VObject)this.mChildren.get(i)).isStateful()) {
          return true;
        }
        i += 1;
      }
      return false;
    }
    
    public boolean onStateChanged(int[] paramArrayOfInt)
    {
      int i = 0;
      boolean bool = false;
      while (i < this.mChildren.size())
      {
        bool |= ((VectorDrawableCompat.VObject)this.mChildren.get(i)).onStateChanged(paramArrayOfInt);
        i += 1;
      }
      return bool;
    }
    
    public void setPivotX(float paramFloat)
    {
      if (paramFloat != this.mPivotX)
      {
        this.mPivotX = paramFloat;
        updateLocalMatrix();
      }
    }
    
    public void setPivotY(float paramFloat)
    {
      if (paramFloat != this.mPivotY)
      {
        this.mPivotY = paramFloat;
        updateLocalMatrix();
      }
    }
    
    public void setRotation(float paramFloat)
    {
      if (paramFloat != this.mRotate)
      {
        this.mRotate = paramFloat;
        updateLocalMatrix();
      }
    }
    
    public void setScaleX(float paramFloat)
    {
      if (paramFloat != this.mScaleX)
      {
        this.mScaleX = paramFloat;
        updateLocalMatrix();
      }
    }
    
    public void setScaleY(float paramFloat)
    {
      if (paramFloat != this.mScaleY)
      {
        this.mScaleY = paramFloat;
        updateLocalMatrix();
      }
    }
    
    public void setTranslateX(float paramFloat)
    {
      if (paramFloat != this.mTranslateX)
      {
        this.mTranslateX = paramFloat;
        updateLocalMatrix();
      }
    }
    
    public void setTranslateY(float paramFloat)
    {
      if (paramFloat != this.mTranslateY)
      {
        this.mTranslateY = paramFloat;
        updateLocalMatrix();
      }
    }
  }
  
  private static abstract class VObject
  {
    public boolean isStateful()
    {
      return false;
    }
    
    public boolean onStateChanged(int[] paramArrayOfInt)
    {
      return false;
    }
  }
  
  private static abstract class VPath
    extends VectorDrawableCompat.VObject
  {
    int mChangingConfigurations;
    protected PathParser.PathDataNode[] mNodes = null;
    String mPathName;
    
    public VPath()
    {
      super();
    }
    
    public VPath(VPath paramVPath)
    {
      super();
      this.mPathName = paramVPath.mPathName;
      this.mChangingConfigurations = paramVPath.mChangingConfigurations;
      this.mNodes = PathParser.deepCopyNodes(paramVPath.mNodes);
    }
    
    public void applyTheme(Resources.Theme paramTheme) {}
    
    public boolean canApplyTheme()
    {
      return false;
    }
    
    public PathParser.PathDataNode[] getPathData()
    {
      return this.mNodes;
    }
    
    public String getPathName()
    {
      return this.mPathName;
    }
    
    public boolean isClipPath()
    {
      return false;
    }
    
    public String nodesToString(PathParser.PathDataNode[] paramArrayOfPathDataNode)
    {
      String str = " ";
      int i = 0;
      while (i < paramArrayOfPathDataNode.length)
      {
        Object localObject = new StringBuilder();
        ((StringBuilder)localObject).append(str);
        ((StringBuilder)localObject).append(paramArrayOfPathDataNode[i].mType);
        ((StringBuilder)localObject).append(":");
        str = ((StringBuilder)localObject).toString();
        localObject = paramArrayOfPathDataNode[i].mParams;
        int j = 0;
        while (j < localObject.length)
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append(str);
          localStringBuilder.append(localObject[j]);
          localStringBuilder.append(",");
          str = localStringBuilder.toString();
          j += 1;
        }
        i += 1;
      }
      return str;
    }
    
    public void printVPath(int paramInt)
    {
      String str = "";
      int i = 0;
      while (i < paramInt)
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(str);
        localStringBuilder.append("    ");
        str = localStringBuilder.toString();
        i += 1;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(str);
      localStringBuilder.append("current path is :");
      localStringBuilder.append(this.mPathName);
      localStringBuilder.append(" pathData is ");
      localStringBuilder.append(nodesToString(this.mNodes));
      Log.v("VectorDrawableCompat", localStringBuilder.toString());
    }
    
    public void setPathData(PathParser.PathDataNode[] paramArrayOfPathDataNode)
    {
      if (!PathParser.canMorph(this.mNodes, paramArrayOfPathDataNode))
      {
        this.mNodes = PathParser.deepCopyNodes(paramArrayOfPathDataNode);
        return;
      }
      PathParser.updateNodes(this.mNodes, paramArrayOfPathDataNode);
    }
    
    public void toPath(Path paramPath)
    {
      paramPath.reset();
      if (this.mNodes != null) {
        PathParser.PathDataNode.nodesToPath(this.mNodes, paramPath);
      }
    }
  }
  
  private static class VPathRenderer
  {
    private static final Matrix IDENTITY_MATRIX = new Matrix();
    float mBaseHeight = 0.0F;
    float mBaseWidth = 0.0F;
    private int mChangingConfigurations;
    Paint mFillPaint;
    private final Matrix mFinalPathMatrix = new Matrix();
    Boolean mIsStateful = null;
    private final Path mPath;
    private PathMeasure mPathMeasure;
    private final Path mRenderPath;
    int mRootAlpha = 255;
    final VectorDrawableCompat.VGroup mRootGroup;
    String mRootName = null;
    Paint mStrokePaint;
    final ArrayMap<String, Object> mVGTargetsMap = new ArrayMap();
    float mViewportHeight = 0.0F;
    float mViewportWidth = 0.0F;
    
    public VPathRenderer()
    {
      this.mRootGroup = new VectorDrawableCompat.VGroup();
      this.mPath = new Path();
      this.mRenderPath = new Path();
    }
    
    public VPathRenderer(VPathRenderer paramVPathRenderer)
    {
      this.mRootGroup = new VectorDrawableCompat.VGroup(paramVPathRenderer.mRootGroup, this.mVGTargetsMap);
      this.mPath = new Path(paramVPathRenderer.mPath);
      this.mRenderPath = new Path(paramVPathRenderer.mRenderPath);
      this.mBaseWidth = paramVPathRenderer.mBaseWidth;
      this.mBaseHeight = paramVPathRenderer.mBaseHeight;
      this.mViewportWidth = paramVPathRenderer.mViewportWidth;
      this.mViewportHeight = paramVPathRenderer.mViewportHeight;
      this.mChangingConfigurations = paramVPathRenderer.mChangingConfigurations;
      this.mRootAlpha = paramVPathRenderer.mRootAlpha;
      this.mRootName = paramVPathRenderer.mRootName;
      if (paramVPathRenderer.mRootName != null) {
        this.mVGTargetsMap.put(paramVPathRenderer.mRootName, this);
      }
      this.mIsStateful = paramVPathRenderer.mIsStateful;
    }
    
    private static float cross(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
    {
      return paramFloat1 * paramFloat4 - paramFloat2 * paramFloat3;
    }
    
    private void drawGroupTree(VectorDrawableCompat.VGroup paramVGroup, Matrix paramMatrix, Canvas paramCanvas, int paramInt1, int paramInt2, ColorFilter paramColorFilter)
    {
      paramVGroup.mStackedMatrix.set(paramMatrix);
      paramVGroup.mStackedMatrix.preConcat(paramVGroup.mLocalMatrix);
      paramCanvas.save();
      int i = 0;
      while (i < paramVGroup.mChildren.size())
      {
        paramMatrix = (VectorDrawableCompat.VObject)paramVGroup.mChildren.get(i);
        if ((paramMatrix instanceof VectorDrawableCompat.VGroup)) {
          drawGroupTree((VectorDrawableCompat.VGroup)paramMatrix, paramVGroup.mStackedMatrix, paramCanvas, paramInt1, paramInt2, paramColorFilter);
        } else if ((paramMatrix instanceof VectorDrawableCompat.VPath)) {
          drawPath(paramVGroup, (VectorDrawableCompat.VPath)paramMatrix, paramCanvas, paramInt1, paramInt2, paramColorFilter);
        }
        i += 1;
      }
      paramCanvas.restore();
    }
    
    private void drawPath(VectorDrawableCompat.VGroup paramVGroup, VectorDrawableCompat.VPath paramVPath, Canvas paramCanvas, int paramInt1, int paramInt2, ColorFilter paramColorFilter)
    {
      float f2 = paramInt1 / this.mViewportWidth;
      float f3 = paramInt2 / this.mViewportHeight;
      float f1 = Math.min(f2, f3);
      paramVGroup = paramVGroup.mStackedMatrix;
      this.mFinalPathMatrix.set(paramVGroup);
      this.mFinalPathMatrix.postScale(f2, f3);
      f2 = getMatrixScale(paramVGroup);
      if (f2 == 0.0F) {
        return;
      }
      paramVPath.toPath(this.mPath);
      paramVGroup = this.mPath;
      this.mRenderPath.reset();
      if (paramVPath.isClipPath())
      {
        this.mRenderPath.addPath(paramVGroup, this.mFinalPathMatrix);
        paramCanvas.clipPath(this.mRenderPath);
        return;
      }
      paramVPath = (VectorDrawableCompat.VFullPath)paramVPath;
      if ((paramVPath.mTrimPathStart != 0.0F) || (paramVPath.mTrimPathEnd != 1.0F))
      {
        float f6 = paramVPath.mTrimPathStart;
        float f7 = paramVPath.mTrimPathOffset;
        float f4 = paramVPath.mTrimPathEnd;
        float f5 = paramVPath.mTrimPathOffset;
        if (this.mPathMeasure == null) {
          this.mPathMeasure = new PathMeasure();
        }
        this.mPathMeasure.setPath(this.mPath, false);
        f3 = this.mPathMeasure.getLength();
        f6 = (f6 + f7) % 1.0F * f3;
        f4 = (f4 + f5) % 1.0F * f3;
        paramVGroup.reset();
        if (f6 > f4)
        {
          this.mPathMeasure.getSegment(f6, f3, paramVGroup, true);
          this.mPathMeasure.getSegment(0.0F, f4, paramVGroup, true);
        }
        else
        {
          this.mPathMeasure.getSegment(f6, f4, paramVGroup, true);
        }
        paramVGroup.rLineTo(0.0F, 0.0F);
      }
      this.mRenderPath.addPath(paramVGroup, this.mFinalPathMatrix);
      Object localObject;
      if (paramVPath.mFillColor.willDraw())
      {
        paramVGroup = paramVPath.mFillColor;
        if (this.mFillPaint == null)
        {
          this.mFillPaint = new Paint(1);
          this.mFillPaint.setStyle(Paint.Style.FILL);
        }
        localObject = this.mFillPaint;
        if (paramVGroup.isGradient())
        {
          paramVGroup = paramVGroup.getShader();
          paramVGroup.setLocalMatrix(this.mFinalPathMatrix);
          ((Paint)localObject).setShader(paramVGroup);
          ((Paint)localObject).setAlpha(Math.round(paramVPath.mFillAlpha * 255.0F));
        }
        else
        {
          ((Paint)localObject).setColor(VectorDrawableCompat.applyAlpha(paramVGroup.getColor(), paramVPath.mFillAlpha));
        }
        ((Paint)localObject).setColorFilter(paramColorFilter);
        Path localPath = this.mRenderPath;
        if (paramVPath.mFillRule == 0) {
          paramVGroup = Path.FillType.WINDING;
        } else {
          paramVGroup = Path.FillType.EVEN_ODD;
        }
        localPath.setFillType(paramVGroup);
        paramCanvas.drawPath(this.mRenderPath, (Paint)localObject);
      }
      if (paramVPath.mStrokeColor.willDraw())
      {
        localObject = paramVPath.mStrokeColor;
        if (this.mStrokePaint == null)
        {
          this.mStrokePaint = new Paint(1);
          this.mStrokePaint.setStyle(Paint.Style.STROKE);
        }
        paramVGroup = this.mStrokePaint;
        if (paramVPath.mStrokeLineJoin != null) {
          paramVGroup.setStrokeJoin(paramVPath.mStrokeLineJoin);
        }
        if (paramVPath.mStrokeLineCap != null) {
          paramVGroup.setStrokeCap(paramVPath.mStrokeLineCap);
        }
        paramVGroup.setStrokeMiter(paramVPath.mStrokeMiterlimit);
        if (((ComplexColorCompat)localObject).isGradient())
        {
          localObject = ((ComplexColorCompat)localObject).getShader();
          ((Shader)localObject).setLocalMatrix(this.mFinalPathMatrix);
          paramVGroup.setShader((Shader)localObject);
          paramVGroup.setAlpha(Math.round(paramVPath.mStrokeAlpha * 255.0F));
        }
        else
        {
          paramVGroup.setColor(VectorDrawableCompat.applyAlpha(((ComplexColorCompat)localObject).getColor(), paramVPath.mStrokeAlpha));
        }
        paramVGroup.setColorFilter(paramColorFilter);
        paramVGroup.setStrokeWidth(paramVPath.mStrokeWidth * (f1 * f2));
        paramCanvas.drawPath(this.mRenderPath, paramVGroup);
      }
    }
    
    private float getMatrixScale(Matrix paramMatrix)
    {
      float[] arrayOfFloat = new float[4];
      float[] tmp7_5 = arrayOfFloat;
      tmp7_5[0] = 0.0F;
      float[] tmp11_7 = tmp7_5;
      tmp11_7[1] = 1.0F;
      float[] tmp15_11 = tmp11_7;
      tmp15_11[2] = 1.0F;
      float[] tmp19_15 = tmp15_11;
      tmp19_15[3] = 0.0F;
      tmp19_15;
      paramMatrix.mapVectors(arrayOfFloat);
      float f1 = (float)Math.hypot(arrayOfFloat[0], arrayOfFloat[1]);
      float f3 = (float)Math.hypot(arrayOfFloat[2], arrayOfFloat[3]);
      float f2 = cross(arrayOfFloat[0], arrayOfFloat[1], arrayOfFloat[2], arrayOfFloat[3]);
      f3 = Math.max(f1, f3);
      f1 = 0.0F;
      if (f3 > 0.0F) {
        f1 = Math.abs(f2) / f3;
      }
      return f1;
    }
    
    public void draw(Canvas paramCanvas, int paramInt1, int paramInt2, ColorFilter paramColorFilter)
    {
      drawGroupTree(this.mRootGroup, IDENTITY_MATRIX, paramCanvas, paramInt1, paramInt2, paramColorFilter);
    }
    
    public float getAlpha()
    {
      return getRootAlpha() / 255.0F;
    }
    
    public int getRootAlpha()
    {
      return this.mRootAlpha;
    }
    
    public boolean isStateful()
    {
      if (this.mIsStateful == null) {
        this.mIsStateful = Boolean.valueOf(this.mRootGroup.isStateful());
      }
      return this.mIsStateful.booleanValue();
    }
    
    public boolean onStateChanged(int[] paramArrayOfInt)
    {
      return this.mRootGroup.onStateChanged(paramArrayOfInt);
    }
    
    public void setAlpha(float paramFloat)
    {
      setRootAlpha((int)(paramFloat * 255.0F));
    }
    
    public void setRootAlpha(int paramInt)
    {
      this.mRootAlpha = paramInt;
    }
  }
  
  private static class VectorDrawableCompatState
    extends Drawable.ConstantState
  {
    boolean mAutoMirrored;
    boolean mCacheDirty;
    boolean mCachedAutoMirrored;
    Bitmap mCachedBitmap;
    int mCachedRootAlpha;
    int[] mCachedThemeAttrs;
    ColorStateList mCachedTint;
    PorterDuff.Mode mCachedTintMode;
    int mChangingConfigurations;
    Paint mTempPaint;
    ColorStateList mTint = null;
    PorterDuff.Mode mTintMode = VectorDrawableCompat.DEFAULT_TINT_MODE;
    VectorDrawableCompat.VPathRenderer mVPathRenderer;
    
    public VectorDrawableCompatState()
    {
      this.mVPathRenderer = new VectorDrawableCompat.VPathRenderer();
    }
    
    public VectorDrawableCompatState(VectorDrawableCompatState paramVectorDrawableCompatState)
    {
      if (paramVectorDrawableCompatState != null)
      {
        this.mChangingConfigurations = paramVectorDrawableCompatState.mChangingConfigurations;
        this.mVPathRenderer = new VectorDrawableCompat.VPathRenderer(paramVectorDrawableCompatState.mVPathRenderer);
        if (paramVectorDrawableCompatState.mVPathRenderer.mFillPaint != null) {
          this.mVPathRenderer.mFillPaint = new Paint(paramVectorDrawableCompatState.mVPathRenderer.mFillPaint);
        }
        if (paramVectorDrawableCompatState.mVPathRenderer.mStrokePaint != null) {
          this.mVPathRenderer.mStrokePaint = new Paint(paramVectorDrawableCompatState.mVPathRenderer.mStrokePaint);
        }
        this.mTint = paramVectorDrawableCompatState.mTint;
        this.mTintMode = paramVectorDrawableCompatState.mTintMode;
        this.mAutoMirrored = paramVectorDrawableCompatState.mAutoMirrored;
      }
    }
    
    public boolean canReuseBitmap(int paramInt1, int paramInt2)
    {
      return (paramInt1 == this.mCachedBitmap.getWidth()) && (paramInt2 == this.mCachedBitmap.getHeight());
    }
    
    public boolean canReuseCache()
    {
      return (!this.mCacheDirty) && (this.mCachedTint == this.mTint) && (this.mCachedTintMode == this.mTintMode) && (this.mCachedAutoMirrored == this.mAutoMirrored) && (this.mCachedRootAlpha == this.mVPathRenderer.getRootAlpha());
    }
    
    public void createCachedBitmapIfNeeded(int paramInt1, int paramInt2)
    {
      if ((this.mCachedBitmap == null) || (!canReuseBitmap(paramInt1, paramInt2)))
      {
        this.mCachedBitmap = Bitmap.createBitmap(paramInt1, paramInt2, Bitmap.Config.ARGB_8888);
        this.mCacheDirty = true;
      }
    }
    
    public void drawCachedBitmapWithRootAlpha(Canvas paramCanvas, ColorFilter paramColorFilter, Rect paramRect)
    {
      paramColorFilter = getPaint(paramColorFilter);
      paramCanvas.drawBitmap(this.mCachedBitmap, null, paramRect, paramColorFilter);
    }
    
    public int getChangingConfigurations()
    {
      return this.mChangingConfigurations;
    }
    
    public Paint getPaint(ColorFilter paramColorFilter)
    {
      if ((!hasTranslucentRoot()) && (paramColorFilter == null)) {
        return null;
      }
      if (this.mTempPaint == null)
      {
        this.mTempPaint = new Paint();
        this.mTempPaint.setFilterBitmap(true);
      }
      this.mTempPaint.setAlpha(this.mVPathRenderer.getRootAlpha());
      this.mTempPaint.setColorFilter(paramColorFilter);
      return this.mTempPaint;
    }
    
    public boolean hasTranslucentRoot()
    {
      return this.mVPathRenderer.getRootAlpha() < 255;
    }
    
    public boolean isStateful()
    {
      return this.mVPathRenderer.isStateful();
    }
    
    @NonNull
    public Drawable newDrawable()
    {
      return new VectorDrawableCompat(this);
    }
    
    @NonNull
    public Drawable newDrawable(Resources paramResources)
    {
      return new VectorDrawableCompat(this);
    }
    
    public boolean onStateChanged(int[] paramArrayOfInt)
    {
      boolean bool = this.mVPathRenderer.onStateChanged(paramArrayOfInt);
      this.mCacheDirty |= bool;
      return bool;
    }
    
    public void updateCacheStates()
    {
      this.mCachedTint = this.mTint;
      this.mCachedTintMode = this.mTintMode;
      this.mCachedRootAlpha = this.mVPathRenderer.getRootAlpha();
      this.mCachedAutoMirrored = this.mAutoMirrored;
      this.mCacheDirty = false;
    }
    
    public void updateCachedBitmap(int paramInt1, int paramInt2)
    {
      this.mCachedBitmap.eraseColor(0);
      Canvas localCanvas = new Canvas(this.mCachedBitmap);
      this.mVPathRenderer.draw(localCanvas, paramInt1, paramInt2, null);
    }
  }
  
  @RequiresApi(24)
  private static class VectorDrawableDelegateState
    extends Drawable.ConstantState
  {
    private final Drawable.ConstantState mDelegateState;
    
    public VectorDrawableDelegateState(Drawable.ConstantState paramConstantState)
    {
      this.mDelegateState = paramConstantState;
    }
    
    public boolean canApplyTheme()
    {
      return this.mDelegateState.canApplyTheme();
    }
    
    public int getChangingConfigurations()
    {
      return this.mDelegateState.getChangingConfigurations();
    }
    
    public Drawable newDrawable()
    {
      VectorDrawableCompat localVectorDrawableCompat = new VectorDrawableCompat();
      localVectorDrawableCompat.mDelegateDrawable = ((VectorDrawable)this.mDelegateState.newDrawable());
      return localVectorDrawableCompat;
    }
    
    public Drawable newDrawable(Resources paramResources)
    {
      VectorDrawableCompat localVectorDrawableCompat = new VectorDrawableCompat();
      localVectorDrawableCompat.mDelegateDrawable = ((VectorDrawable)this.mDelegateState.newDrawable(paramResources));
      return localVectorDrawableCompat;
    }
    
    public Drawable newDrawable(Resources paramResources, Resources.Theme paramTheme)
    {
      VectorDrawableCompat localVectorDrawableCompat = new VectorDrawableCompat();
      localVectorDrawableCompat.mDelegateDrawable = ((VectorDrawable)this.mDelegateState.newDrawable(paramResources, paramTheme));
      return localVectorDrawableCompat;
    }
  }
}


/* Location:              C:\Users\lixia\Desktop\android反编译三件套\dex2jar-2.0\classes-dex2jar.jar!\android\support\graphics\drawable\VectorDrawableCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */