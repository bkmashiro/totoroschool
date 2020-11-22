package com.tencent.smtt.sdk;

import android.content.Context;
import android.webkit.ValueCallback;
import com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext;
import java.net.URL;

public final class JsContext
{
  private final JsVirtualMachine a;
  private final IX5JsContext b;
  private ExceptionHandler c;
  private String d;
  
  public JsContext(Context paramContext)
  {
    this(new JsVirtualMachine(paramContext));
  }
  
  public JsContext(JsVirtualMachine paramJsVirtualMachine)
  {
    if (paramJsVirtualMachine != null)
    {
      this.a = paramJsVirtualMachine;
      this.b = this.a.a();
    }
    try
    {
      this.b.setPerContextData(this);
      return;
    }
    catch (AbstractMethodError paramJsVirtualMachine) {}
    throw new IllegalArgumentException("The virtualMachine value can not be null");
  }
  
  public static JsContext current()
  {
    return (JsContext)X5JsCore.a();
  }
  
  public void addJavascriptInterface(Object paramObject, String paramString)
  {
    this.b.addJavascriptInterface(paramObject, paramString);
  }
  
  public void destroy()
  {
    this.b.destroy();
  }
  
  public void evaluateJavascript(String paramString, ValueCallback<String> paramValueCallback)
  {
    evaluateJavascript(paramString, paramValueCallback, null);
  }
  
  public void evaluateJavascript(String paramString, ValueCallback<String> paramValueCallback, URL paramURL)
  {
    this.b.evaluateJavascript(paramString, paramValueCallback, paramURL);
  }
  
  public JsValue evaluateScript(String paramString)
  {
    return evaluateScript(paramString, null);
  }
  
  public JsValue evaluateScript(String paramString, URL paramURL)
  {
    paramString = this.b.evaluateScript(paramString, paramURL);
    if (paramString == null) {
      return null;
    }
    return new JsValue(this, paramString);
  }
  
  public void evaluateScriptAsync(String paramString, ValueCallback<JsValue> paramValueCallback, URL paramURL)
  {
    if (paramValueCallback == null) {
      paramValueCallback = null;
    } else {
      paramValueCallback = new c(this, paramValueCallback);
    }
    this.b.evaluateScriptAsync(paramString, paramValueCallback, paramURL);
  }
  
  public ExceptionHandler exceptionHandler()
  {
    return this.c;
  }
  
  public String name()
  {
    return this.d;
  }
  
  public void removeJavascriptInterface(String paramString)
  {
    this.b.removeJavascriptInterface(paramString);
  }
  
  public void setExceptionHandler(ExceptionHandler paramExceptionHandler)
  {
    this.c = paramExceptionHandler;
    IX5JsContext localIX5JsContext;
    if (paramExceptionHandler == null) {
      localIX5JsContext = this.b;
    }
    for (paramExceptionHandler = null;; paramExceptionHandler = new d(this))
    {
      localIX5JsContext.setExceptionHandler(paramExceptionHandler);
      return;
      localIX5JsContext = this.b;
    }
  }
  
  public void setName(String paramString)
  {
    this.d = paramString;
    this.b.setName(paramString);
  }
  
  public void stealValueFromOtherCtx(String paramString1, JsContext paramJsContext, String paramString2)
  {
    this.b.stealValueFromOtherCtx(paramString1, paramJsContext.b, paramString2);
  }
  
  public JsVirtualMachine virtualMachine()
  {
    return this.a;
  }
  
  public static abstract interface ExceptionHandler
  {
    public abstract void handleException(JsContext paramJsContext, JsError paramJsError);
  }
}


/* Location:              C:\Users\lixia\Desktop\android反编译三件套\dex2jar-2.0\classes-dex2jar.jar!\com\tencent\smtt\sdk\JsContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */