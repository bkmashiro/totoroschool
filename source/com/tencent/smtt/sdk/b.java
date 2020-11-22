package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import com.tencent.smtt.sdk.a.d;

class b
  implements com.tencent.smtt.export.external.interfaces.DownloadListener
{
  private DownloadListener a;
  private WebView b;
  
  public b(WebView paramWebView, DownloadListener paramDownloadListener, boolean paramBoolean)
  {
    this.a = paramDownloadListener;
    this.b = paramWebView;
  }
  
  public void onDownloadStart(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong)
  {
    onDownloadStart(paramString1, null, null, paramString2, paramString3, paramString4, paramLong, null, null);
  }
  
  public void onDownloadStart(String paramString1, String paramString2, byte[] paramArrayOfByte, String paramString3, String paramString4, String paramString5, long paramLong, String paramString6, String paramString7)
  {
    if (this.a == null)
    {
      if (QbSdk.canOpenMimeFileType(this.b.getContext(), paramString5))
      {
        paramString2 = new Intent("com.tencent.QQBrowser.action.sdk.document");
        paramString2.setFlags(268435456);
        paramString2.putExtra("key_reader_sdk_url", paramString1);
        paramString2.putExtra("key_reader_sdk_type", 1);
        paramString2.setData(Uri.parse(paramString1));
        this.b.getContext().startActivity(paramString2);
        return;
      }
      paramString2 = this.b.getContext().getApplicationInfo();
      if ((paramString2 == null) || (!"com.tencent.mm".equals(paramString2.packageName))) {
        d.a(this.b.getContext(), paramString1, null, null);
      }
    }
    else
    {
      this.a.onDownloadStart(paramString1, paramString3, paramString4, paramString5, paramLong);
    }
  }
  
  public void onDownloadVideo(String paramString, long paramLong, int paramInt) {}
}


/* Location:              C:\Users\lixia\Desktop\android反编译三件套\dex2jar-2.0\classes-dex2jar.jar!\com\tencent\smtt\sdk\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */