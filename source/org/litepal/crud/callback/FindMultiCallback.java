package org.litepal.crud.callback;

import java.util.List;

public abstract interface FindMultiCallback<T>
{
  public abstract void onFinish(List<T> paramList);
}


/* Location:              C:\Users\lixia\Desktop\android反编译三件套\dex2jar-2.0\classes-dex2jar.jar!\org\litepal\crud\callback\FindMultiCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */