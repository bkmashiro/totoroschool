package com.tencent.smtt.sdk;

import java.io.File;
import java.io.FileFilter;

class ar
  implements FileFilter
{
  ar(aq paramaq) {}
  
  public boolean accept(File paramFile)
  {
    return paramFile.getName().endsWith(".dex");
  }
}


/* Location:              C:\Users\lixia\Desktop\android反编译三件套\dex2jar-2.0\classes-dex2jar.jar!\com\tencent\smtt\sdk\ar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */