package com.loc;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public abstract class aa
{
  aa a;
  
  aa() {}
  
  aa(aa paramaa)
  {
    this.a = paramaa;
  }
  
  protected abstract byte[] a(byte[] paramArrayOfByte)
    throws CertificateException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException;
  
  public final byte[] b(byte[] paramArrayOfByte)
    throws CertificateException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException
  {
    byte[] arrayOfByte = paramArrayOfByte;
    if (this.a != null) {
      arrayOfByte = this.a.b(paramArrayOfByte);
    }
    return a(arrayOfByte);
  }
}


/* Location:              C:\Users\lixia\Desktop\android反编译三件套\dex2jar-2.0\classes-dex2jar.jar!\com\loc\aa.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */