package com.tencent.smtt.export.external.jscore.interfaces;

import java.nio.ByteBuffer;

public abstract interface IX5JsValue
{
  public abstract IX5JsValue call(Object[] paramArrayOfObject);
  
  public abstract IX5JsValue construct(Object[] paramArrayOfObject);
  
  public abstract boolean isArray();
  
  public abstract boolean isArrayBufferOrArrayBufferView();
  
  public abstract boolean isBoolean();
  
  public abstract boolean isFunction();
  
  public abstract boolean isInteger();
  
  public abstract boolean isJavascriptInterface();
  
  public abstract boolean isNull();
  
  public abstract boolean isNumber();
  
  public abstract boolean isObject();
  
  public abstract boolean isPromise();
  
  public abstract boolean isString();
  
  public abstract boolean isUndefined();
  
  public abstract void resolveOrReject(Object paramObject, boolean paramBoolean);
  
  public abstract boolean toBoolean();
  
  public abstract ByteBuffer toByteBuffer();
  
  public abstract int toInteger();
  
  public abstract Object toJavascriptInterface();
  
  public abstract Number toNumber();
  
  public abstract <T> T toObject(Class<T> paramClass);
  
  public abstract String toString();
  
  public static abstract interface JsValueFactory
  {
    public abstract String getJsValueClassName();
    
    public abstract IX5JsValue unwrap(Object paramObject);
    
    public abstract Object wrap(IX5JsValue paramIX5JsValue);
  }
}


/* Location:              C:\Users\lixia\Desktop\android反编译三件套\dex2jar-2.0\classes-dex2jar.jar!\com\tencent\smtt\export\external\jscore\interfaces\IX5JsValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */