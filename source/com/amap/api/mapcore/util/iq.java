package com.amap.api.mapcore.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class iq
  extends iw
{
  ByteArrayOutputStream a = new ByteArrayOutputStream();
  
  public iq() {}
  
  public iq(iw paramiw)
  {
    super(paramiw);
  }
  
  protected byte[] a(byte[] paramArrayOfByte)
  {
    paramArrayOfByte = this.a.toByteArray();
    try
    {
      this.a.close();
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
    this.a = new ByteArrayOutputStream();
    return paramArrayOfByte;
  }
  
  public void b(byte[] paramArrayOfByte)
  {
    try
    {
      this.a.write(paramArrayOfByte);
      return;
    }
    catch (Throwable paramArrayOfByte)
    {
      paramArrayOfByte.printStackTrace();
    }
  }
}


/* Location:              C:\Users\lixia\Desktop\android反编译三件套\dex2jar-2.0\classes-dex2jar.jar!\com\amap\api\mapcore\util\iq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */