package com.totoro.school.frament.score;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.gson.e;
import com.totoro.school.adapter.score.ScoreMorningNewAdapter;
import com.totoro.school.e.g;
import com.totoro.school.entity.login.login.LoginReturnModel;
import com.totoro.school.entity.score.morning.ScoreMorningBaseModel;
import com.totoro.school.entity.score.morning.ScoreMorningModel;
import com.totoro.school.entity.score.morning.ScoreMorningRequestModel;
import com.totoro.school.entity.score.morning.ScoreMorningReturnModel;
import com.totoro.school.entity.score.term.SchoolTermModel;
import com.totoro.school.entity.score.term.SchoolTermRequestModel;
import com.totoro.school.entity.score.term.SchoolTermReturnModel;
import com.totoro.school.utilpub.network.a.b;
import com.totoro.school.utilpub.network.c;
import com.totoro.school.utils.i;
import com.totoro.school.view.wheelview.WheelView;
import com.totoro.school.view.wheelview.WheelView.b;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ScoreMorningFragment
  extends Fragment
  implements c
{
  private g a;
  private ScoreMorningNewAdapter b;
  private List<ScoreMorningModel> c = new ArrayList();
  @BindView(2131296319)
  TextView centerNumber;
  private List<SchoolTermModel> d = new ArrayList();
  private SchoolTermModel e;
  private int f = 0;
  private LoginReturnModel g;
  @BindView(2131296439)
  TextView leftNumber;
  @BindView(2131296488)
  LinearLayout noDataView;
  @BindView(2131296536)
  TextView rightNumber;
  @BindView(2131296562)
  RecyclerView runRecyclerView;
  @BindView(2131296560)
  LinearLayout scoreLayout;
  @BindView(2131296734)
  RelativeLayout yearLayout;
  @BindView(2131296735)
  TextView yearName;
  
  private void b()
  {
    if (this.g != null)
    {
      SchoolTermRequestModel localSchoolTermRequestModel = new SchoolTermRequestModel();
      localSchoolTermRequestModel.setSchoolCode(this.g.getSchoolID());
      localSchoolTermRequestModel.setReqNum("0");
      this.a.a(localSchoolTermRequestModel);
    }
  }
  
  private void b(String paramString)
  {
    if (this.g != null)
    {
      ScoreMorningRequestModel localScoreMorningRequestModel = new ScoreMorningRequestModel();
      localScoreMorningRequestModel.setSchoolName(this.g.getSchoolID());
      localScoreMorningRequestModel.setSnCode(this.g.getSnCode());
      localScoreMorningRequestModel.setAcademicYear(paramString);
      localScoreMorningRequestModel.setFlag("T");
      this.a.a(localScoreMorningRequestModel);
    }
  }
  
  private void c() {}
  
  private void d()
  {
    this.yearLayout.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ScoreMorningFragment.a(ScoreMorningFragment.this);
      }
    });
  }
  
  private void e()
  {
    LinearLayoutManager localLinearLayoutManager = new LinearLayoutManager(getContext());
    this.runRecyclerView.setLayoutManager(localLinearLayoutManager);
    this.b = new ScoreMorningNewAdapter(this.c, getActivity());
    this.runRecyclerView.setAdapter(this.b);
  }
  
  private void f()
  {
    final com.totoro.school.view.dialog.a locala = new com.totoro.school.view.dialog.a(getContext(), 2131755009);
    View localView = LayoutInflater.from(getContext()).inflate(2131493001, null);
    Object localObject = (TextView)localView.findViewById(2131296332);
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.d.iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(((SchoolTermModel)localIterator.next()).getTermname());
    }
    ((TextView)localObject).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ScoreMorningFragment.this.yearName.setText(ScoreMorningFragment.b(ScoreMorningFragment.this).getTermname());
        ScoreMorningFragment.a(ScoreMorningFragment.this, ScoreMorningFragment.b(ScoreMorningFragment.this).getTermid());
        if (locala.isShowing()) {
          locala.dismiss();
        }
      }
    });
    localObject = (WheelView)localView.findViewById(2131296730);
    ((WheelView)localObject).a(localArrayList, this.f);
    ((WheelView)localObject).setOnItemSelectedListener(new WheelView.b()
    {
      public void a(int paramAnonymousInt, String paramAnonymousString)
      {
        ScoreMorningFragment.a(ScoreMorningFragment.this, (SchoolTermModel)ScoreMorningFragment.c(ScoreMorningFragment.this).get(paramAnonymousInt));
        ScoreMorningFragment.a(ScoreMorningFragment.this, paramAnonymousInt);
      }
    });
    locala.setContentView(localView);
    locala.setCancelable(false);
    locala.show();
  }
  
  public void a(String paramString, Object paramObject)
  {
    int i = paramString.hashCode();
    int j = 1;
    int k = -1;
    if (i != -1594411680)
    {
      if ((i == -1138171104) && (paramString.equals("query_morning_score")))
      {
        i = 1;
        break label60;
      }
    }
    else if (paramString.equals("query_school_term"))
    {
      i = 0;
      break label60;
    }
    i = -1;
    switch (i)
    {
    default: 
      
    case 1: 
      paramString = (ScoreMorningReturnModel)b.a(paramObject, ScoreMorningReturnModel.class);
      if (paramString != null)
      {
        paramObject = paramString.getCode();
        i = ((String)paramObject).hashCode();
        if (i != 1715960)
        {
          switch (i)
          {
          default: 
            break;
          case 1507425: 
            if (!((String)paramObject).equals("1002")) {
              break;
            }
            i = 3;
            break;
          case 1507424: 
            if (!((String)paramObject).equals("1001")) {
              break;
            }
            i = 2;
            break;
          case 1507423: 
            if (!((String)paramObject).equals("1000")) {
              break;
            }
            i = j;
            break;
          }
        }
        else if (((String)paramObject).equals("8000"))
        {
          i = 0;
          break label210;
        }
        i = -1;
        switch (i)
        {
        default: 
          return;
        case 1: 
        case 2: 
        case 3: 
          this.leftNumber.setText("0");
          this.centerNumber.setText("0");
          this.rightNumber.setText("0");
          this.c.clear();
          this.b.notifyDataSetChanged();
          this.noDataView.setVisibility(0);
          this.scoreLayout.setVisibility(8);
          return;
        }
        if ((paramString.getMorQueryList() != null) && (paramString.getMorQueryList().length() > 0))
        {
          paramObject = (List)new e().a(paramString.getMorQueryList(), new com.google.gson.c.a() {}.b());
          if ((paramObject != null) && (((List)paramObject).size() > 0))
          {
            paramObject = (ScoreMorningBaseModel)((List)paramObject).get(0);
            j = Integer.parseInt(((ScoreMorningBaseModel)paramObject).getNumberOfRequests()) - Integer.parseInt(((ScoreMorningBaseModel)paramObject).getQfTimes());
            i = j;
            if (j < 0) {
              i = 0;
            }
            this.leftNumber.setText(((ScoreMorningBaseModel)paramObject).getQfTimes());
            TextView localTextView = this.centerNumber;
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("");
            localStringBuilder.append(i);
            localTextView.setText(localStringBuilder.toString());
            this.rightNumber.setText(((ScoreMorningBaseModel)paramObject).getNumberOfRequests());
          }
        }
        this.c.clear();
        if ((paramString.getMonthTimeList() != null) && (paramString.getMonthTimeList().length() > 0))
        {
          paramString = (List)new e().a(paramString.getMonthTimeList(), new com.google.gson.c.a() {}.b());
          this.c.addAll(paramString);
        }
        paramString = this.b;
        ScoreMorningNewAdapter.a = this.e.getTermid();
        this.b.notifyDataSetChanged();
        if ((this.c != null) && (this.c.size() > 0))
        {
          this.noDataView.setVisibility(8);
          this.scoreLayout.setVisibility(0);
          return;
        }
        this.noDataView.setVisibility(0);
        this.scoreLayout.setVisibility(8);
        return;
      }
      break;
    case 0: 
      label60:
      label210:
      paramString = (SchoolTermReturnModel)b.a(paramObject, SchoolTermReturnModel.class);
      if (paramString != null)
      {
        paramObject = paramString.getCode();
        if (((String)paramObject).hashCode() != 1715960)
        {
          i = k;
        }
        else
        {
          i = k;
          if (((String)paramObject).equals("8000")) {
            i = 0;
          }
        }
        if (i != 0) {
          return;
        }
        if ((paramString.getTermlist() != null) && (paramString.getTermlist().length() > 0))
        {
          this.d.clear();
          this.d = ((List)new e().a(paramString.getTermlist(), new com.google.gson.c.a() {}.b()));
          i = 0;
          while (i < this.d.size())
          {
            if ("Y".equals(((SchoolTermModel)this.d.get(i)).getFlag()))
            {
              this.e = ((SchoolTermModel)this.d.get(i));
              this.f = i;
              this.yearName.setText(this.e.getTermname());
              b(this.e.getTermid());
            }
            i += 1;
          }
          if ((this.d.size() > 0) && (this.e == null))
          {
            this.e = ((SchoolTermModel)this.d.get(0));
            this.yearName.setText(this.e.getTermname());
            b(this.e.getTermid());
          }
        }
      }
      break;
    }
  }
  
  public void a(String paramString, Throwable paramThrowable) {}
  
  public void a_(String paramString) {}
  
  public void d_() {}
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2131492949, paramViewGroup, false);
    ButterKnife.bind(this, paramLayoutInflater);
    this.g = i.a();
    this.a = new g(this);
    b();
    c();
    d();
    e();
    return paramLayoutInflater;
  }
}


/* Location:              C:\Users\lixia\Desktop\android反编译三件套\dex2jar-2.0\classes-dex2jar.jar!\com\totoro\school\frament\score\ScoreMorningFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */