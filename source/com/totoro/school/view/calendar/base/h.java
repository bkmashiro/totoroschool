package com.totoro.school.view.calendar.base;

import android.content.Context;
import android.content.res.Resources;

public final class h
{
  private static final double[] A = { 3.81034392032D, 8399.68473021D, -3.31919929753E-5D, 3.20170955005E-8D, -1.53637455544E-10D };
  private static double B = 0.0D;
  private static double C = 0.0D;
  private static String[] a;
  private static final double[] b = { 0.0D, 50287.92262D, 111.24406D, 0.07699D, -0.23479D, -0.00178D, 1.8E-4D, 1.0E-5D };
  private static final double[] c = { -4000.0D, 108371.7D, -13036.8D, 392.0D, 0.0D, -500.0D, 17201.0D, -627.82D, 16.17D, -0.3413D, -150.0D, 12200.6D, -346.41D, 5.403D, -0.1593D, 150.0D, 9113.8D, -328.13D, -1.647D, 0.0377D, 500.0D, 5707.5D, -391.41D, 0.915D, 0.3145D, 900.0D, 2203.4D, -283.45D, 13.034D, -0.1778D, 1300.0D, 490.1D, -57.35D, 2.085D, -0.0072D, 1600.0D, 120.0D, -9.81D, -1.532D, 0.1403D, 1700.0D, 10.2D, -0.91D, 0.51D, -0.037D, 1800.0D, 13.4D, -0.72D, 0.202D, -0.0193D, 1830.0D, 7.8D, -1.81D, 0.416D, -0.0247D, 1860.0D, 8.3D, -0.13D, -0.406D, 0.0292D, 1880.0D, -5.4D, 0.32D, -0.183D, 0.0173D, 1900.0D, -2.3D, 2.06D, 0.169D, -0.0135D, 1920.0D, 21.2D, 1.69D, -0.304D, 0.0167D, 1940.0D, 24.2D, 1.22D, -0.064D, 0.0031D, 1960.0D, 33.2D, 0.51D, 0.231D, -0.0109D, 1980.0D, 51.0D, 1.29D, -0.026D, 0.0032D, 2000.0D, 64.7D, -1.66D, 5.224D, -0.2905D, 2150.0D, 279.4D, 732.95D, 429.579D, 0.0158D, 6000.0D };
  private static final double[] d = { 0.016708634D, -4.2037E-5D, -1.267E-7D };
  private static final double[] e = { 1.796595680777785D, 0.030010238356341697D, 8.028514559173916E-6D };
  private static final double[] f = { 4.89506311081711D, 628.3319667475674D, 5.291887161265863E-6D, 0.0D, 0.0D };
  private static final double[] g = { 2.1824391966D, -33.757045954D, 3.62262E-5D, 3.734E-8D, -2.8793E-10D, -171996.0D, -1742.0D, 92025.0D, 89.0D, 3.5069406862D, 1256.663930738D, 1.05845E-5D, 6.9813E-10D, -2.2815E-10D, -13187.0D, -16.0D, 5736.0D, -31.0D, 1.3375032491D, 16799.418221925D, -5.11866E-5D, 6.4626E-8D, -5.3543E-10D, -2274.0D, -2.0D, 977.0D, -5.0D, 4.3648783932D, -67.514091907D, 7.24525E-5D, 7.4681E-8D, -5.7586E-10D, 2062.0D, 2.0D, -895.0D, 5.0D, 0.0431251803D, -628.301955171D, 2.682E-6D, 6.5935E-10D, 5.5705E-11D, -1426.0D, 34.0D, 54.0D, -1.0D, 2.3555557435D, 8328.691425719D, 1.545547E-4D, 2.5033E-7D, -1.1863E-9D, 712.0D, 1.0D, -7.0D, 0.0D, 3.4638155059D, 1884.965885909D, 7.9025E-6D, 3.8785E-11D, -2.8386E-10D, -517.0D, 12.0D, 224.0D, -6.0D, 5.4382493597D, 16833.175267879D, -8.74129E-5D, 2.7285E-8D, -2.475E-10D, -386.0D, -4.0D, 200.0D, 0.0D, 3.6930589926D, 25128.109647645D, 1.033681E-4D, 3.1496E-7D, -1.7218E-9D, -301.0D, 0.0D, 129.0D, -1.0D, 3.5500658664D, 628.361975567D, 1.32664E-5D, 1.3575E-9D, -1.7245E-10D, 217.0D, -5.0D, -95.0D, 3.0D };
  private static final double[] h = { 1.75347045673D, 0.0D, 0.0D, 0.03341656456D, 4.66925680417D, 6283.0758499914D, 3.4894275E-4D, 4.62610241759D, 12566.1516999828D, 3.417571E-5D, 2.82886579606D, 3.523118349D, 3.497056E-5D, 2.74411800971D, 5753.3848848968D, 3.135896E-5D, 3.62767041758D, 77713.7714681205D, 2.676218E-5D, 4.41808351397D, 7860.4193924392D, 2.342687E-5D, 6.13516237631D, 3930.2096962196D, 1.273166E-5D, 2.03709655772D, 529.6909650946D, 1.324292E-5D, 0.74246356352D, 11506.7697697936D, 9.01855E-6D, 2.04505443513D, 26.2983197998D, 1.199167E-5D, 1.10962944315D, 1577.3435424478D, 8.57223E-6D, 3.50849156957D, 398.1490034082D, 7.79786E-6D, 1.17882652114D, 5223.6939198022D, 9.9025E-6D, 5.23268129594D, 5884.9268465832D, 7.53141E-6D, 2.53339053818D, 5507.5532386674D, 5.05264E-6D, 4.58292563052D, 18849.2275499742D, 4.92379E-6D, 4.20506639861D, 775.522611324D, 3.56655E-6D, 2.91954116867D, 0.0673103028D, 2.84125E-6D, 1.89869034186D, 796.2980068164D, 2.4281E-6D, 0.34481140906D, 5486.777843175D, 3.17087E-6D, 5.84901952218D, 11790.6290886588D, 2.71039E-6D, 0.31488607649D, 10977.078804699D, 2.0616E-6D, 4.80646606059D, 2544.3144198834D, 2.05385E-6D, 1.86947813692D, 5573.1428014331D, 2.02261E-6D, 2.45767795458D, 6069.7767545534D, 1.26184E-6D, 1.0830263021D, 20.7753954924D, 1.55516E-6D, 0.83306073807D, 213.299095438D, 1.15132E-6D, 0.64544911683D, 0.9803210682D, 1.02851E-6D, 0.63599846727D, 4694.0029547076D, 1.01724E-6D, 4.26679821365D, 7.1135470008D, 9.9206E-7D, 6.20992940258D, 2146.1654164752D, 1.32212E-6D, 3.41118275555D, 2942.4634232916D, 9.7607E-7D, 0.6810127227D, 155.4203994342D, 8.5128E-7D, 1.29870743025D, 6275.9623029906D, 7.4651E-7D, 1.75508916159D, 5088.6288397668D, 1.01895E-6D, 0.97569221824D, 15720.8387848784D, 8.4711E-7D, 3.67080093025D, 71430.6956181291D, 7.3547E-7D, 4.67926565481D, 801.8209311238D, 7.3874E-7D, 3.50319443167D, 3154.6870848956D, 7.8756E-7D, 3.03698313141D, 12036.4607348882D, 7.9637E-7D, 1.807913307D, 17260.1546546904D, 8.5803E-7D, 5.98322631256D, 161000.6857376741D, 5.6963E-7D, 2.78430398043D, 6286.5989683404D, 6.1148E-7D, 1.81839811024D, 7084.8967811152D, 6.9627E-7D, 0.83297596966D, 9437.762934887D, 5.6116E-7D, 4.38694880779D, 14143.4952424306D, 6.2449E-7D, 3.97763880587D, 8827.3902698748D, 5.1145E-7D, 0.28306864501D, 5856.4776591154D, 5.5577E-7D, 3.47006009062D, 6279.5527316424D, 4.1036E-7D, 5.36817351402D, 8429.2412664666D, 5.1605E-7D, 1.33282746983D, 1748.016413067D, 5.1992E-7D, 0.18914945834D, 12139.5535091068D, 4.9E-7D, 0.48735065033D, 1194.4470102246D, 3.92E-7D, 6.16832995016D, 10447.3878396044D, 3.5566E-7D, 1.77597314691D, 6812.766815086D, 3.677E-7D, 6.04133859347D, 10213.285546211D, 3.6596E-7D, 2.56955238628D, 1059.3819301892D, 3.3291E-7D, 0.59309499459D, 17789.845619785D, 3.5954E-7D, 1.70876111898D, 2352.8661537718D };
  private static final double[] i = { 6283.31966747491D, 0.0D, 0.0D, 0.00206058863D, 2.67823455584D, 6283.0758499914D, 4.30343E-5D, 2.63512650414D, 12566.1516999828D, 4.25264E-6D, 1.59046980729D, 3.523118349D, 1.08977E-6D, 2.96618001993D, 1577.3435424478D, 9.3478E-7D, 2.59212835365D, 18849.2275499742D, 1.19261E-6D, 5.79557487799D, 26.2983197998D, 7.2122E-7D, 1.13846158196D, 529.6909650946D, 6.7768E-7D, 1.87472304791D, 398.1490034082D, 6.7327E-7D, 4.40918235168D, 5507.5532386674D, 5.9027E-7D, 2.8879703846D, 5223.6939198022D, 5.5976E-7D, 2.17471680261D, 155.4203994342D, 4.5407E-7D, 0.39803079805D, 796.2980068164D, 3.6369E-7D, 0.46624739835D, 775.522611324D, 2.8958E-7D, 2.64707383882D, 7.1135470008D, 1.9097E-7D, 1.84628332577D, 5486.777843175D, 2.0844E-7D, 5.34138275149D, 0.9803210682D, 1.8508E-7D, 4.96855124577D, 213.299095438D, 1.6233E-7D, 0.03216483047D, 2544.3144198834D, 1.7293E-7D, 2.99116864949D, 6275.9623029906D };
  private static final double[] j = { 5.291887E-4D, 0.0D, 0.0D, 8.719837E-5D, 1.07209665242D, 6283.0758499914D, 3.09125E-6D, 0.86728818832D, 12566.1516999828D, 2.7339E-7D, 0.05297871691D, 3.523118349D, 1.6334E-7D, 5.18826691036D, 26.2983197998D, 1.5752E-7D, 3.6845788943D, 155.4203994342D, 9.541E-8D, 0.75742297675D, 18849.2275499742D, 8.937E-8D, 2.05705419118D, 77713.7714681205D, 6.952E-8D, 0.8267330541D, 775.522611324D, 5.064E-8D, 4.66284525271D, 1577.3435424478D };
  private static final double[] k = { 2.89226E-6D, 5.84384198723D, 6283.0758499914D, 3.4955E-7D, 0.0D, 0.0D, 1.6819E-7D, 5.48766912348D, 12566.1516999828D };
  private static final double[] l = { 1.14084E-6D, 3.14159265359D, 0.0D, 7.717E-8D, 4.13446589358D, 6283.0758499914D, 7.65E-9D, 3.83803776214D, 12566.1516999828D };
  private static final double[] m = { 8.78E-9D, 3.14159265359D, 0.0D };
  private static final double[] n = { 2.7962E-6D, 3.19870156017D, 84334.6615813083D, 1.01643E-6D, 5.42248619256D, 5507.5532386674D, 8.0445E-7D, 3.88013204458D, 5223.6939198022D, 4.3806E-7D, 3.70444689758D, 2352.8661537718D, 3.1933E-7D, 4.00026369781D, 1577.3435424478D, 2.2724E-7D, 3.9847383156D, 1047.7473117547D, 1.6392E-7D, 3.56456119782D, 5856.4776591154D, 1.8141E-7D, 4.98367470263D, 6283.0758499914D, 1.4443E-7D, 3.70275614914D, 9437.762934887D, 1.4304E-7D, 3.41117857525D, 10213.285546211D };
  private static final double[] o = { 9.03E-8D, 3.8972906189D, 5507.5532386674D, 6.177E-8D, 1.73038850355D, 5223.6939198022D };
  private static final double[] p = { 1.00013988799D, 0.0D, 0.0D, 0.01670699626D, 3.09846350771D, 6283.0758499914D, 1.3956023E-4D, 3.0552460962D, 12566.1516999828D, 3.08372E-5D, 5.19846674381D, 77713.7714681205D, 1.628461E-5D, 1.17387749012D, 5753.3848848968D, 1.575568E-5D, 2.84685245825D, 7860.4193924392D, 9.24799E-6D, 5.45292234084D, 11506.7697697936D, 5.42444E-6D, 4.56409149777D, 3930.2096962196D };
  private static final double[] q = { 0.00103018608D, 1.10748969588D, 6283.0758499914D, 1.721238E-5D, 1.06442301418D, 12566.1516999828D, 7.02215E-6D, 3.14159265359D, 0.0D };
  private static final double[] r = { 4.359385E-5D, 5.78455133738D, 6283.0758499914D };
  private static final double[] s = { 1.44595E-6D, 4.27319435148D, 6283.0758499914D };
  private static final double[] t = { 22639.58588D, 2.3555545723D, 8328.6914247251D, 1.5231275E-4D, 2.5041111E-7D, -1.1863391E-9D, 4586.4383203D, 8.0413790709D, 7214.0628654588D, -2.1850087E-4D, -1.8646419E-7D, 8.7760973E-10D, 2369.9139357D, 10.3969336431D, 15542.754290184D, -6.6188121E-5D, 6.3946925E-8D, -3.0872935E-10D, 769.0257187D, 4.7111091445D, 16657.3828494503D, 3.046255E-4D, 5.0082223E-7D, -2.3726782E-9D, -666.4175399D, -0.0431256817D, 628.3019552485D, -2.6638815E-6D, 6.1639211E-10D, -5.4439728E-11D, -411.5957339D, 3.2558104895D, 16866.932315281D, -1.2804259E-4D, -9.8998954E-9D, 4.0433461E-11D, 211.6555524D, 5.6858244986D, -1114.6285592663D, -3.7081362E-4D, -4.368753E-7D, 2.0639488E-9D, 205.435953D, 8.0845047526D, 6585.7609102104D, -2.1583699E-4D, -1.8708058E-7D, 9.3204945E-10D, 191.9561973D, 12.7524882154D, 23871.4457149091D, 8.6124629E-5D, 3.1435804E-7D, -1.4950684E-9D, 164.7286185D, 10.4400593249D, 14914.4523349355D, -6.352424E-5D, 6.3330532E-8D, -2.5428962E-10D, -147.3213842D, -2.398680254D, -7700.3894694766D, -1.5497663E-4D, -2.4979472E-7D, 1.1318993E-9D, -124.9881185D, 5.1984668216D, 7771.377145092D, -3.3094061E-5D, 3.1973462E-8D, -1.5436468E-10D, -109.3803637D, 2.3124288905D, 8956.9933799736D, 1.4964887E-4D, 2.5102751E-7D, -1.2407788E-9D, 55.1770578D, 7.1411231536D, -1324.178025097D, 6.1854469E-5D, 7.384682E-8D, -3.4916281E-10D, -45.0996092D, 5.6113650618D, 25195.6237400061D, 2.4270161E-5D, 2.4051122E-7D, -1.1459056E-9D, 39.533301D, -0.9002559173D, -8538.2408905558D, 2.8035534E-4D, 2.6031101E-7D, -1.2267725E-9D, 38.4298346D, 18.438312714D, 22756.8171556428D, -2.8468899E-4D, -1.2251727E-7D, 5.6888037E-10D, 36.1238141D, 7.0666637168D, 24986.0742741754D, 4.5693825E-4D, 7.5123334E-7D, -3.5590172E-9D, 30.7725751D, 16.0827581417D, 14428.1257309177D, -4.3700174E-4D, -3.7292838E-7D, 1.7552195E-9D, -28.3971008D, 7.9982533891D, 7842.3648207073D, -2.2116475E-4D, -1.858478E-7D, 8.2317E-10D, -24.3582283D, 10.3538079614D, 16171.0562454324D, -6.8852003E-5D, 6.4563317E-8D, -3.6316908E-10D, -18.5847068D, 2.8429122493D, -557.3142796331D, -1.8540681E-4D, -2.1843765E-7D, 1.0319744E-9D, 17.9544674D, 5.1553411398D, 8399.6791003405D, -3.5757942E-5D, 3.2589854E-8D, -2.088044E-10D, 14.5302779D, 12.7956138971D, 23243.1437596606D, 8.8788511E-5D, 3.1374165E-7D, -1.4406287E-9D, 14.3796974D, 15.1080427876D, 32200.1371396342D, 2.3843738E-4D, 5.6476915E-7D, -2.6814075E-9D, 14.2514576D, -24.081036632D, -2.3011998397D, 1.5231275E-4D, 2.5041111E-7D, -1.1863391E-9D, 13.8990596D, 20.7938672862D, 31085.5085803679D, -1.3237624E-4D, 1.2789385E-7D, -6.174587E-10D, 13.1940636D, 3.3302699264D, -9443.3199839914D, -5.2312637E-4D, -6.8728642E-7D, 3.2502879E-9D, -9.6790568D, -4.7542348263D, -16029.0808942018D, -3.0728938E-4D, -5.0020584E-7D, 2.3182384E-9D, -9.3658635D, 11.2971895604D, 24080.9951807398D, -3.4654346E-4D, -1.9636409E-7D, 9.1804319E-10D, 8.6055318D, 5.7289501804D, -1742.9305145148D, -3.6814974E-4D, -4.374917E-7D, 2.1183885E-9D, -8.4530982D, 7.5540213938D, 16100.0685698171D, 1.1921869E-4D, 2.8238458E-7D, -1.3407038E-9D, 8.0501724D, 10.4831850066D, 14286.150379687D, -6.0860358E-5D, 6.271414E-8D, -1.998499E-10D, -7.6301553D, 4.6679834628D, 17285.6848046987D, 3.0196162E-4D, 5.0143862E-7D, -2.4271179E-9D, -7.4474952D, -0.0862513635D, 1256.603910497D, -5.327763E-6D, 1.2327842E-9D, -1.0887946E-10D, 7.3712011D, 8.1276304344D, 5957.4589549619D, -2.1317311E-4D, -1.8769697E-7D, 9.8648918E-10D, 7.06299D, 0.9591375719D, 33.7570471374D, -3.0829302E-5D, -3.6967043E-8D, 1.7385419E-10D, -6.3831491D, 9.4966777258D, 7004.5133996281D, 2.1416722E-4D, 3.2425793E-7D, -1.5355019E-9D, -5.7416071D, 13.6527441326D, 32409.6866054649D, -1.9423071E-4D, 5.4047029E-8D, -2.6829589E-10D, 4.3740095D, 18.4814383957D, 22128.5152003943D, -2.8202511E-4D, -1.2313366E-7D, 6.233201E-10D, -3.9976134D, 7.966919634D, 33524.3151647312D, 1.7658291E-4D, 4.9092233E-7D, -2.3322447E-9D, -3.2096876D, 13.2398458924D, 14985.4400105508D, -2.5159493E-4D, -1.5449073E-7D, 7.2324505E-10D, -2.9145404D, 12.7093625336D, 24499.7476701576D, 8.3460748E-5D, 3.1497443E-7D, -1.5495082E-9D, 2.731889D, 16.1258838235D, 13799.8237756692D, -4.3433786E-4D, -3.7354477E-7D, 1.8096592E-9D, -2.5679459D, -2.4418059357D, -7072.0875142282D, -1.5764051E-4D, -2.4917833E-7D, 1.0774596E-9D, -2.521199D, 7.9551277074D, 8470.6667759558D, -2.2382863E-4D, -1.8523141E-7D, 7.6873027E-10D, 2.4888871D, 5.6426988169D, -486.3266040178D, -3.734775E-4D, -4.3625891E-7D, 2.0095091E-9D, 2.1460741D, 7.1842488353D, -1952.4799803455D, 6.451835E-5D, 7.3230428E-8D, -2.9472308E-10D, 1.977727D, 23.1494218585D, 39414.200005093D, 1.9936508E-5D, 3.7830496E-7D, -1.8037978E-9D, 1.9336825D, 9.422218289D, 33314.7656989005D, 6.09251E-4D, 1.0016445E-6D, -4.7453563E-9D, 1.8707647D, 20.836992968D, 30457.2066251194D, -1.2971236E-4D, 1.2727746E-7D, -5.6301898E-10D, -1.7529659D, 0.4873576771D, -8886.0057043583D, -3.3771956E-4D, -4.6884877E-7D, 2.2183135E-9D, -1.4371624D, 7.0979974718D, -695.8760698485D, 5.9190587E-5D, 7.4463212E-8D, -4.0360254E-10D, -1.3725701D, 1.455298655D, -209.5494658307D, 4.3266809E-4D, 5.1072212E-7D, -2.4131116E-9D, 1.2618162D, 7.5108957121D, 16728.3705250656D, 1.1655481E-4D, 2.8300097E-7D, -1.3951435E-9D };
  private static final double[] u = { 1.6768D, -0.0431256817D, 628.3019552485D, -2.6638815E-6D, 6.1639211E-10D, -5.4439728E-11D, 0.51642D, 11.2260974062D, 6585.7609102104D, -2.1583699E-4D, -1.8708058E-7D, 9.3204945E-10D, 0.41383D, 13.5816519784D, 14914.4523349355D, -6.352424E-5D, 6.3330532E-8D, -2.5428962E-10D, 0.37115D, 5.5402729076D, 7700.3894694766D, 1.5497663E-4D, 2.4979472E-7D, -1.1318993E-9D, 0.2756D, 2.3124288905D, 8956.9933799736D, 1.4964887E-4D, 2.5102751E-7D, -1.2407788E-9D, 0.2459863D, -25.6198212459D, -2.3011998397D, 1.5231275E-4D, 2.5041111E-7D, -1.1863391E-9D, 0.07118D, 7.9982533891D, 7842.3648207073D, -2.2116475E-4D, -1.858478E-7D, 8.2317E-10D, 0.06128D, 10.3538079614D, 16171.0562454324D, -6.8852003E-5D, 6.4563317E-8D, -3.6316908E-10D };
  private static final double[] v = { 0.00487D, -0.0431256817D, 628.3019552485D, -2.6638815E-6D, 6.1639211E-10D, -5.4439728E-11D, 0.00228D, -27.1705318325D, -2.3011998397D, 1.5231275E-4D, 2.5041111E-7D, -1.1863391E-9D, 0.0015D, 11.2260974062D, 6585.7609102104D, -2.1583699E-4D, -1.8708058E-7D, 9.3204945E-10D };
  private static final double[] w = { 18461.24006D, 1.6279052448D, 8433.4661576405D, -6.4021295E-5D, -4.9499477E-9D, 2.0216731E-11D, 1010.1671484D, 3.983459817D, 16762.1575823656D, 8.8291456E-5D, 2.4546117E-7D, -1.1661223E-9D, 999.6936555D, 0.7276493275D, -104.7747329154D, 2.1633405E-4D, 2.5536106E-7D, -1.2065558E-9D, 623.6524746D, 8.7690283983D, 7109.2881325435D, -2.1668263E-6D, 6.8896872E-8D, -3.2894608E-10D, 199.4837596D, 9.6692843156D, 15647.5290230993D, -2.8252217E-4D, -1.9141414E-7D, 8.9782646E-10D, 166.5741153D, 6.4134738261D, -1219.4032921817D, -1.5447958E-4D, -1.8151424E-7D, 8.57393E-10D, 117.2606951D, 12.0248388879D, 23976.2204478244D, -1.3020942E-4D, 5.8996977E-8D, -2.8851262E-10D, 61.9119504D, 6.3390143893D, 25090.8490070907D, 2.4060421E-4D, 4.9587228E-7D, -2.3524614E-9D, 33.3572027D, 11.1245829706D, 15437.9795572686D, 1.5014592E-4D, 3.1930799E-7D, -1.5152852E-9D, 31.7596709D, 3.0832038997D, 8223.9166918098D, 3.686468E-4D, 5.0577218E-7D, -2.3928949E-9D, 29.5766003D, 8.8121540801D, 6480.986177295D, 4.9705523E-7D, 6.828048E-8D, -2.7450635E-10D, 15.5662654D, 4.0579192538D, -9548.0947169068D, -3.0679233E-4D, -4.3192536E-7D, 2.0437321E-9D, 15.1215543D, 14.3803934601D, 32304.9118725496D, 2.2103334E-5D, 3.0940809E-7D, -1.4748517E-9D, -12.0941511D, 8.7259027166D, 7737.590087792D, -4.8307078E-6D, 6.9513264E-8D, -3.8338581E-10D, 8.8681426D, 9.7124099974D, 15019.2270678508D, -2.7985829E-4D, -1.9203053E-7D, 9.5226618E-10D, 8.04504D, 0.6687636586D, 8399.709110503D, -3.3191993E-5D, 3.2017096E-8D, -1.5363746E-10D, 7.9585542D, 12.0679645696D, 23347.918492576D, -1.2754553E-4D, 5.8380585E-8D, -2.3407289E-10D, 7.434555D, 6.4565995078D, -1847.7052474301D, -1.518157E-4D, -1.8213063E-7D, 9.1183272E-10D, -6.7314363D, -4.0265854988D, -16133.8556271171D, -9.0955337E-5D, -2.4484477E-7D, 1.1116826E-9D, 6.579575D, 16.8104074692D, 14323.3509980023D, -2.206677E-4D, -1.1756732E-7D, 5.4866364E-10D, -6.4600721D, 1.584779563D, 9061.768112889D, -6.6685176E-5D, -4.3335556E-9D, -3.4222998E-11D, -6.2964773D, 4.8837157343D, 25300.3984729215D, -1.9206388E-4D, -1.4849843E-8D, 6.0650192E-11D, -5.6323538D, -0.7707750092D, 733.0766881638D, -2.1899793E-4D, -2.5474467E-7D, 1.1521161E-9D, -5.3683961D, 6.8263720663D, 16204.8433027325D, -9.7115356E-5D, 2.7023515E-8D, -1.3414795E-10D, -5.3112784D, 3.9403341353D, 17390.4595376141D, 8.5627574E-5D, 2.4607756E-7D, -1.2205621E-9D, -5.0759179D, 0.6845236457D, 523.5272223331D, 2.1367016E-4D, 2.5597745E-7D, -1.2609955E-9D, -4.8396143D, -1.6710309265D, -7805.164202392D, 6.1357413E-5D, 5.5663398E-9D, -7.4656459E-11D, -4.8057401D, 3.5705615768D, -662.0890125485D, 3.0927234E-5D, 3.692341E-8D, -1.7458141E-10D, 3.9840545D, 8.6945689615D, 33419.5404318159D, 3.9291696E-4D, 7.462834E-7D, -3.5388005E-9D, 3.6744619D, 19.1659620415D, 22652.0424227274D, -6.8354947E-5D, 1.328438E-7D, -6.3767543E-10D, 2.9984815D, 20.0662179587D, 31190.2833132833D, -3.4871029E-4D, -1.2746721E-7D, 5.890971E-10D, 2.7986413D, -2.528161162D, -16971.7070481963D, 3.4437664E-4D, 2.6526096E-7D, -1.2469893E-9D, 2.4138774D, 17.7106633865D, 22861.5918885581D, -5.0102304E-4D, -3.7787833E-7D, 1.7754362E-9D, 2.1863132D, 5.5132179088D, -9757.6441827375D, 1.2587576E-4D, 7.8796768E-8D, -3.6937954E-10D, 2.1461692D, 13.4801375428D, 23766.6709819937D, 3.0245868E-4D, 5.697191E-7D, -2.7016242E-9D, 1.7659832D, 11.1677086523D, 14809.6776020201D, 1.5280981E-4D, 3.1869159E-7D, -1.4608454E-9D, -1.6244212D, 7.3137297434D, 7318.8375983742D, -4.3483492E-4D, -4.4182525E-7D, 2.0841655E-9D, 1.5813036D, 5.438758472D, 16552.6081165349D, 5.2095955E-4D, 7.5618329E-7D, -3.579234E-9D, 1.5197528D, 16.7359480324D, 40633.6032972747D, 1.7441609E-4D, 5.5981921E-7D, -2.6611908E-9D, 1.5156341D, 1.7023646816D, -17876.7861416319D, -4.5910508E-4D, -6.8233647E-7D, 3.2300712E-9D, 1.5102092D, 5.497729645D, 8399.6847301375D, -3.3094061E-5D, 3.1973462E-8D, -1.5436468E-10D, -1.3178223D, 9.6261586339D, 16275.8309783478D, -2.8518605E-4D, -1.9079775E-7D, 8.4338673E-10D, -1.2642739D, 11.9817132061D, 24604.5224030729D, -1.328733E-4D, 5.9613369E-8D, -3.4295235E-10D, 1.1918723D, 22.421772531D, 39518.9747380084D, -1.9639754E-4D, 1.229439E-7D, -5.9724197E-10D, 1.134611D, 14.4235191419D, 31676.6099173011D, 2.4767216E-5D, 3.087917E-7D, -1.420412E-9D, 1.085781D, 8.8552797618D, 5852.6842220465D, 3.1609367E-6D, 6.7664088E-8D, -2.2006663E-10D, -1.0193852D, 7.2392703065D, 33629.0898976466D, -3.9751134E-5D, 2.3556127E-7D, -1.1256889E-9D, -0.8227141D, 11.0814572888D, 16066.2815125171D, 1.4748204E-4D, 3.1992438E-7D, -1.5697249E-9D, 0.8042238D, 3.527435895D, -33.7870573D, 2.8263353E-5D, 3.7539802E-8D, -2.2902113E-10D, 0.8025939D, 6.7832463846D, 16833.1452579809D, -9.9779237E-5D, 2.7639907E-8D, -1.8858767E-10D, -0.7931866D, -6.382140071D, -24462.5470518423D, -2.4326809E-4D, -4.9525589E-7D, 2.2980217E-9D, -0.7910153D, 6.3703481443D, -591.1013369332D, -1.5714346E-4D, -1.8089785E-7D, 8.0295327E-10D, -0.6674056D, 9.1819266386D, 24533.5347274576D, 5.5197395E-5D, 2.7743463E-7D, -1.320487E-9D, 0.6502226D, 4.1010449356D, -10176.3966721553D, -3.0412845E-4D, -4.3254175E-7D, 2.0981718E-9D, -0.6388131D, 6.2958887075D, 25719.1509623392D, 2.3794032E-4D, 4.9648867E-7D, -2.4069012E-9D };
  private static final double[] x = { 0.0743D, 11.9537467337D, 6480.986177295D, 4.9705523E-7D, 6.828048E-8D, -2.7450635E-10D, 0.03043D, 8.7259027166D, 7737.590087792D, -4.8307078E-6D, 6.9513264E-8D, -3.8338581E-10D, 0.02229D, 12.854002651D, 15019.2270678508D, -2.7985829E-4D, -1.9203053E-7D, 9.5226618E-10D, 0.01999D, 15.2095572232D, 23347.918492576D, -1.2754553E-4D, 5.8380585E-8D, -2.3407289E-10D, 0.01869D, 9.5981921614D, -1847.7052474301D, -1.518157E-4D, -1.8213063E-7D, 9.1183272E-10D, 0.01696D, 7.1681781524D, 16133.8556271171D, 9.0955337E-5D, 2.4484477E-7D, -1.1116826E-9D, 0.01623D, 1.584779563D, 9061.768112889D, -6.6685176E-5D, -4.3335556E-9D, -3.4222998E-11D, 0.01419D, -0.7707750092D, 733.0766881638D, -2.1899793E-4D, -2.5474467E-7D, 1.1521161E-9D };
  private static final double[] y = { 385000.5290396D, 1.5707963268D, 0.0D, 0.0D, 0.0D, 0.0D, -20905.3551378D, 3.926350899D, 8328.6914247251D, 1.5231275E-4D, 2.5041111E-7D, -1.1863391E-9D, -3699.110933D, 9.6121753977D, 7214.0628654588D, -2.1850087E-4D, -1.8646419E-7D, 8.7760973E-10D, -2955.9675626D, 11.9677299699D, 15542.754290184D, -6.6188121E-5D, 6.3946925E-8D, -3.0872935E-10D, -569.9251264D, 6.2819054713D, 16657.3828494503D, 3.046255E-4D, 5.0082223E-7D, -2.3726782E-9D, 246.1584797D, 7.2566208254D, -1114.6285592663D, -3.7081362E-4D, -4.368753E-7D, 2.0639488E-9D, -204.5861179D, 12.0108556517D, 14914.4523349355D, -6.352424E-5D, 6.3330532E-8D, -2.5428962E-10D, -170.7330791D, 14.3232845422D, 23871.4457149091D, 8.6124629E-5D, 3.1435804E-7D, -1.4950684E-9D, -152.1378118D, 9.6553010794D, 6585.7609102104D, -2.1583699E-4D, -1.8708058E-7D, 9.3204945E-10D, -129.6202242D, -0.8278839272D, -7700.3894694766D, -1.5497663E-4D, -2.4979472E-7D, 1.1318993E-9D, 108.7427014D, 6.7692631483D, 7771.377145092D, -3.3094061E-5D, 3.1973462E-8D, -1.5436468E-10D, 104.7552944D, 3.8832252173D, 8956.9933799736D, 1.4964887E-4D, 2.5102751E-7D, -1.2407788E-9D, 79.6605685D, 0.6705404095D, -8538.2408905558D, 2.8035534E-4D, 2.6031101E-7D, -1.2267725E-9D, 48.8883284D, 1.527670645D, 628.3019552485D, -2.6638815E-6D, 6.1639211E-10D, -5.4439728E-11D, -34.7825237D, 20.0091090408D, 22756.8171556428D, -2.8468899E-4D, -1.2251727E-7D, 5.6888037E-10D, 30.8238599D, 11.9246042882D, 16171.0562454324D, -6.8852003E-5D, 6.4563317E-8D, -3.6316908E-10D, 24.2084985D, 9.5690497159D, 7842.3648207073D, -2.2116475E-4D, -1.858478E-7D, 8.2317E-10D, -23.2104305D, 8.6374600436D, 24986.0742741754D, 4.5693825E-4D, 7.5123334E-7D, -3.5590172E-9D, -21.6363439D, 17.6535544685D, 14428.1257309177D, -4.3700174E-4D, -3.7292838E-7D, 1.7552195E-9D, -16.6747239D, 6.7261374666D, 8399.6791003405D, -3.5757942E-5D, 3.2589854E-8D, -2.088044E-10D, 14.402689D, 4.9010662531D, -9443.3199839914D, -5.2312637E-4D, -6.8728642E-7D, 3.2502879E-9D, -12.8314035D, 14.3664102239D, 23243.1437596606D, 8.8788511E-5D, 3.1374165E-7D, -1.4406287E-9D, -11.6499478D, 22.364663613D, 31085.5085803679D, -1.3237624E-4D, 1.2789385E-7D, -6.174587E-10D, -10.4447578D, 16.6788391144D, 32200.1371396342D, 2.3843738E-4D, 5.6476915E-7D, -2.6814075E-9D, 10.3211071D, 8.7119194804D, -1324.178025097D, 6.1854469E-5D, 7.384682E-8D, -3.4916281E-10D, 10.0562033D, 7.2997465071D, -1742.9305145148D, -3.6814974E-4D, -4.374917E-7D, 2.1183885E-9D, -9.8844667D, 12.0539813334D, 14286.150379687D, -6.0860358E-5D, 6.271414E-8D, -1.998499E-10D, 8.7515625D, 6.3563649081D, -9652.8694498221D, -9.0458282E-5D, -1.7656429E-7D, 8.3717626E-10D, -8.3791067D, 4.4137085761D, -557.3142796331D, -1.8540681E-4D, -2.1843765E-7D, 1.0319744E-9D, -7.0026961D, -3.1834384995D, -16029.0808942018D, -3.0728938E-4D, -5.0020584E-7D, 2.3182384E-9D, 6.3220032D, 9.1248177206D, 16100.0685698171D, 1.1921869E-4D, 2.8238458E-7D, -1.3407038E-9D, 5.7508579D, 6.2387797896D, 17285.6848046987D, 3.0196162E-4D, 5.0143862E-7D, -2.4271179E-9D, -4.9501349D, 9.6984267611D, 5957.4589549619D, -2.1317311E-4D, -1.8769697E-7D, 9.8648918E-10D, -4.421177D, 3.0260949818D, -209.5494658307D, 4.3266809E-4D, 5.1072212E-7D, -2.4131116E-9D, 4.1311145D, 11.0674740526D, 7004.5133996281D, 2.1416722E-4D, 3.2425793E-7D, -1.5355019E-9D, -3.9579827D, 20.0522347225D, 22128.5152003943D, -2.8202511E-4D, -1.2313366E-7D, 6.233201E-10D, 3.2582371D, 14.8106422192D, 14985.4400105508D, -2.5159493E-4D, -1.5449073E-7D, 7.2324505E-10D, -3.148302D, 4.8266068163D, 16866.932315281D, -1.2804259E-4D, -9.8998954E-9D, 4.0433461E-11D, 2.6164092D, 14.2801588604D, 24499.7476701576D, 8.3460748E-5D, 3.1497443E-7D, -1.5495082E-9D, 2.353631D, 9.5259240342D, 8470.6667759558D, -2.2382863E-4D, -1.8523141E-7D, 7.6873027E-10D, -2.1171283D, -0.871009609D, -7072.0875142282D, -1.5764051E-4D, -2.4917833E-7D, 1.0774596E-9D, -1.8970368D, 17.6966801503D, 13799.8237756692D, -4.3433786E-4D, -3.7354477E-7D, 1.8096592E-9D, -1.7385258D, 2.0581540038D, -8886.0057043583D, -3.3771956E-4D, -4.6884877E-7D, 2.2183135E-9D, -1.5713944D, 22.4077892948D, 30457.2066251194D, -1.2971236E-4D, 1.2727746E-7D, -5.6301898E-10D, -1.4225541D, 24.7202181853D, 39414.200005093D, 1.9936508E-5D, 3.7830496E-7D, -1.8037978E-9D, -1.4189284D, 17.1661967915D, 23314.1314352759D, -9.9282182E-5D, 9.5920387E-8D, -4.6309403E-10D, 1.1655364D, 3.8400995356D, 9585.2953352221D, 1.4698499E-4D, 2.516439E-7D, -1.2952185E-9D, -1.1169371D, 10.9930146158D, 33314.7656989005D, 6.09251E-4D, 1.0016445E-6D, -4.7453563E-9D, 1.0656723D, 1.4845449633D, 1256.603910497D, -5.327763E-6D, 1.2327842E-9D, -1.0887946E-10D, 1.058619D, 11.9220903668D, 8364.7398411275D, -2.1850087E-4D, -1.8646419E-7D, 8.7760973E-10D, -0.9333176D, 9.0816920389D, 16728.3705250656D, 1.1655481E-4D, 2.8300097E-7D, -1.3951435E-9D, 0.8624328D, 12.455087647D, 6656.7485858257D, -4.0390768E-4D, -4.0490184E-7D, 1.9095841E-9D, 0.8512404D, 4.3705828944D, 70.9876756153D, -1.8807069E-4D, -2.1782126E-7D, 9.7753467E-10D, -0.8488018D, 16.7219647962D, 31571.8351843857D, 2.4110126E-4D, 5.6415276E-7D, -2.6269678E-9D, -0.7956264D, 3.5134526588D, -9095.555170189D, 9.4948529E-5D, 4.1873358E-8D, -1.9479814E-10D };
  private static final double[] z = { 0.51395D, 12.0108556517D, 14914.4523349355D, -6.352424E-5D, 6.3330532E-8D, -2.5428962E-10D, 0.38245D, 9.6553010794D, 6585.7609102104D, -2.1583699E-4D, -1.8708058E-7D, 9.3204945E-10D, 0.32654D, 3.9694765808D, 7700.3894694766D, 1.5497663E-4D, 2.4979472E-7D, -1.1318993E-9D, 0.26396D, 0.7416325637D, 8956.9933799736D, 1.4964887E-4D, 2.5102751E-7D, -1.2407788E-9D, 0.12302D, -1.6139220085D, 628.3019552485D, -2.6638815E-6D, 6.1639211E-10D, -5.4439728E-11D, 0.07754D, 8.7830116346D, 16171.0562454324D, -6.8852003E-5D, 6.4563317E-8D, -3.6316908E-10D, 0.06068D, 6.4274570623D, 7842.3648207073D, -2.2116475E-4D, -1.858478E-7D, 8.2317E-10D, 0.0497D, 12.0539813334D, 14286.150379687D, -6.0860358E-5D, 6.271414E-8D, -1.998499E-10D };
  
  private static double a(double paramDouble1, double paramDouble2, int paramInt)
  {
    double d1;
    if (paramInt == 0) {
      d1 = 360.0D + paramDouble1;
    } else {
      d1 = 25.0D + paramDouble1;
    }
    double d6 = 0.017453292519943295D * paramDouble2;
    double d2 = a(paramInt, paramDouble1, d6);
    double d3 = a(paramInt, d1, d6);
    paramDouble2 = d3;
    if (d2 < d3) {
      paramDouble2 = d3 - 6.283185307179586D;
    }
    int i1 = 0;
    double d5 = 0.0D;
    d3 = 1.0D;
    double d4 = paramDouble1;
    paramDouble1 = d1;
    while (i1 < 10)
    {
      d1 = (paramDouble2 - d2) / (paramDouble1 - d4);
      if (Math.abs(d1) > 1.0E-15D) {
        d3 = d1;
      }
      d1 = d4 - d2 / d3;
      d2 = a(paramInt, d1, d6);
      if (d2 > 1.0D) {
        d2 -= 6.283185307179586D;
      }
      if (Math.abs(d2) < 1.0E-8D) {
        return d1;
      }
      i1 += 1;
      d5 = d2;
      d4 = paramDouble1;
      paramDouble1 = d1;
      d2 = paramDouble2;
      paramDouble2 = d5;
      d5 = d1;
    }
    return d5;
  }
  
  private static double a(int paramInt, double paramDouble1, double paramDouble2)
  {
    double[] arrayOfDouble = g(paramDouble1);
    arrayOfDouble[0] += 3.141592653589793D;
    arrayOfDouble[1] = (-arrayOfDouble[1]);
    b(paramDouble1, arrayOfDouble);
    if (paramInt == 0)
    {
      a locala = f(paramDouble1);
      arrayOfDouble[0] += a.a(locala);
      return c(paramDouble2 - arrayOfDouble[0]);
    }
    return c(paramDouble2 - (h(paramDouble1)[0] - arrayOfDouble[0]));
  }
  
  private static double a(double[] paramArrayOfDouble)
  {
    double d1 = 0.0D;
    int i1 = 0;
    while (i1 < paramArrayOfDouble.length)
    {
      d1 += paramArrayOfDouble[i1] * Math.cos(paramArrayOfDouble[(i1 + 1)] + B * paramArrayOfDouble[(i1 + 2)]);
      i1 += 3;
    }
    return d1;
  }
  
  private static b a(double paramDouble, boolean paramBoolean)
  {
    b localb = new b(null);
    double d1 = paramDouble;
    if (paramBoolean) {
      d1 = paramDouble - e(paramDouble - 2451545.0D);
    }
    double d2 = d1 + 0.5D;
    d1 = b(d2);
    paramDouble = d1;
    if (d1 > 2299161.0D)
    {
      paramDouble = b((d1 - 1867216.25D) / 36524.25D);
      paramDouble = d1 + (1.0D + paramDouble - b(paramDouble / 4.0D));
    }
    paramDouble += 1524.0D;
    b.a(localb, b((paramDouble - 122.1D) / 365.25D));
    paramDouble -= b(b.a(localb) * 365.25D);
    b.b(localb, b(paramDouble / 30.6001D));
    b.c(localb, paramDouble - b(b.b(localb) * 30.6001D));
    b.a(localb, b.a(localb) - 4716.0D);
    b.e(localb);
    if (b.b(localb) > 12.0D) {
      b.b(localb, b.b(localb) - 12.0D);
    }
    if (b.b(localb) <= 2.0D) {
      b.f(localb);
    }
    paramDouble = (d2 - d1) * 24.0D;
    b.d(localb, b(paramDouble));
    paramDouble = (paramDouble - b.d(localb)) * 60.0D;
    b.e(localb, b(paramDouble));
    b.f(localb, (paramDouble - b.c(localb)) * 60.0D);
    return localb;
  }
  
  private static void a(double paramDouble, double[] paramArrayOfDouble)
  {
    double d2 = paramDouble / 365250.0D;
    int i1 = 1;
    double d1 = 1.0D;
    paramDouble = 0.0D;
    while (i1 < 8)
    {
      d1 *= d2;
      paramDouble += b[i1] * d1;
      i1 += 1;
    }
    paramArrayOfDouble[0] = c(paramArrayOfDouble[0] + (paramDouble + d2 * 2.9965D) / 206264.80624709636D);
  }
  
  static void a(Context paramContext)
  {
    a = paramContext.getResources().getStringArray(2130903045);
  }
  
  public static String[] a(int paramInt)
  {
    String[] arrayOfString = new String[24];
    int i1 = paramInt - 1;
    Object localObject1 = b(i1);
    Object localObject2 = c(i1);
    System.arraycopy(localObject1, 0, arrayOfString, 0, localObject1.length);
    System.arraycopy(localObject2, 0, arrayOfString, 22, localObject2.length);
    double d1 = paramInt - 2000;
    Double.isNaN(d1);
    paramInt = 0;
    while (paramInt < 19)
    {
      double d2 = paramInt;
      Double.isNaN(d2);
      localObject1 = a(a(d2 * 15.2D + d1 * 365.2422D, paramInt * 15, 0) + 2451545.0D + 0.3333333333333333D, true);
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(((b)localObject1).toString());
      ((StringBuilder)localObject2).append(a[paramInt]);
      arrayOfString[(paramInt + 3)] = ((StringBuilder)localObject2).toString();
      paramInt += 1;
    }
    return arrayOfString;
  }
  
  private static double b(double paramDouble)
  {
    paramDouble = Math.floor(paramDouble);
    if (paramDouble < 0.0D) {
      return paramDouble + 1.0D;
    }
    return paramDouble;
  }
  
  private static double b(double[] paramArrayOfDouble)
  {
    double d2 = C;
    double d3 = d2 * d2;
    double d4 = d3 * d2;
    double d1 = 0.0D;
    int i1 = 0;
    while (i1 < paramArrayOfDouble.length)
    {
      d1 += paramArrayOfDouble[i1] * Math.sin(paramArrayOfDouble[(i1 + 1)] + paramArrayOfDouble[(i1 + 2)] * d2 + paramArrayOfDouble[(i1 + 3)] * d3 + paramArrayOfDouble[(i1 + 4)] * d4 + paramArrayOfDouble[(i1 + 5)] * (d4 * d2));
      i1 += 6;
    }
    return d1;
  }
  
  private static void b(double paramDouble, double[] paramArrayOfDouble)
  {
    double d1 = paramDouble / 36525.0D;
    double d2 = d1 * d1;
    double d6 = d2 * d1;
    double d7 = f[0];
    double d8 = f[1];
    double d9 = f[2];
    double d10 = f[3];
    double d11 = f[4];
    double d3 = e[0];
    double d4 = e[1];
    double d5 = e[2];
    paramDouble = d[0] + d[1] * d1 + d[2] * d2;
    d6 = d7 + d8 * d1 + d9 * d2 + d10 * d6 + d11 * (d6 * d1) - paramArrayOfDouble[0];
    d1 = d3 + d4 * d1 + d5 * d2 - paramArrayOfDouble[0];
    paramArrayOfDouble[0] -= (Math.cos(d6) - Math.cos(d1) * paramDouble) * 9.936508497454117E-5D / Math.cos(paramArrayOfDouble[1]);
    paramArrayOfDouble[1] -= Math.sin(paramArrayOfDouble[1]) * 9.936508497454117E-5D * (Math.sin(d6) - paramDouble * Math.sin(d1));
    paramArrayOfDouble[0] = c(paramArrayOfDouble[0]);
  }
  
  private static String[] b(int paramInt)
  {
    String[] arrayOfString = new String[3];
    double d1 = paramInt - 2000;
    Double.isNaN(d1);
    paramInt = 21;
    while (paramInt < 24)
    {
      double d2 = paramInt;
      Double.isNaN(d2);
      b localb = a(a(d2 * 15.2D + d1 * 365.2422D, paramInt * 15, 0) + 2451545.0D + 0.3333333333333333D, true);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(localb.toString());
      localStringBuilder.append(a[paramInt]);
      arrayOfString[(paramInt - 21)] = localStringBuilder.toString();
      paramInt += 1;
    }
    return arrayOfString;
  }
  
  private static double c(double paramDouble)
  {
    paramDouble %= 6.283185307179586D;
    if (paramDouble < 0.0D) {
      return paramDouble + 6.283185307179586D;
    }
    return paramDouble;
  }
  
  private static String[] c(int paramInt)
  {
    String[] arrayOfString = new String[2];
    double d1 = paramInt - 2000;
    Double.isNaN(d1);
    paramInt = 19;
    while (paramInt < 21)
    {
      double d2 = paramInt;
      Double.isNaN(d2);
      b localb = a(a(d2 * 15.2D + d1 * 365.2422D, paramInt * 15, 0) + 2451545.0D + 0.3333333333333333D, true);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(localb.toString());
      localStringBuilder.append(a[paramInt]);
      arrayOfString[(paramInt - 19)] = localStringBuilder.toString();
      paramInt += 1;
    }
    return arrayOfString;
  }
  
  private static double d(double paramDouble)
  {
    int i2;
    for (int i1 = 0; i1 < 100; i1 = i2)
    {
      double[] arrayOfDouble = c;
      i2 = i1 + 5;
      if ((paramDouble < arrayOfDouble[i2]) || (i1 == 95)) {
        break;
      }
    }
    paramDouble = (paramDouble - c[i1]) / (c[(i1 + 5)] - c[i1]) * 10.0D;
    double d1 = paramDouble * paramDouble;
    return c[(i1 + 1)] + c[(i1 + 2)] * paramDouble + c[(i1 + 3)] * d1 + c[(i1 + 4)] * (d1 * paramDouble);
  }
  
  private static double e(double paramDouble)
  {
    return d(paramDouble / 365.2425D + 2000.0D) / 86400.0D;
  }
  
  private static a f(double paramDouble)
  {
    a locala = new a(null);
    a.a(locala, 0.0D);
    a.b(locala, 0.0D);
    paramDouble /= 36525.0D;
    double d1 = paramDouble * paramDouble;
    double d2 = d1 * paramDouble;
    int i1 = 0;
    while (i1 < g.length)
    {
      double d3 = g[i1] + g[(i1 + 1)] * paramDouble + g[(i1 + 2)] * d1 + g[(i1 + 3)] * d2 + g[(i1 + 4)] * (d2 * paramDouble);
      a.a(locala, a.a(locala) + (g[(i1 + 5)] + g[(i1 + 6)] * paramDouble / 10.0D) * Math.sin(d3));
      a.b(locala, a.b(locala) + (g[(i1 + 7)] + g[(i1 + 8)] * paramDouble / 10.0D) * Math.cos(d3));
      i1 += 9;
    }
    a.a(locala, a.a(locala) / 2.0626480624709637E9D);
    a.b(locala, a.b(locala) / 2.0626480624709637E9D);
    return locala;
  }
  
  private static double[] g(double paramDouble)
  {
    B = paramDouble / 365250.0D;
    double[] arrayOfDouble = new double[3];
    paramDouble = B;
    double d1 = paramDouble * paramDouble;
    double d2 = d1 * paramDouble;
    double d3 = d2 * paramDouble;
    arrayOfDouble[0] = (a(h) + a(i) * paramDouble + a(j) * d1 + a(k) * d2 + a(l) * d3 + a(m) * (d3 * paramDouble));
    arrayOfDouble[1] = (a(n) + a(o) * paramDouble);
    arrayOfDouble[2] = (a(p) + a(q) * paramDouble + a(r) * d1 + a(s) * d2);
    arrayOfDouble[0] = c(arrayOfDouble[0]);
    return arrayOfDouble;
  }
  
  private static double[] h(double paramDouble)
  {
    C = paramDouble / 36525.0D;
    double d1 = C;
    double d2 = d1 * d1;
    double d3 = d2 * d1;
    double[] arrayOfDouble = new double[3];
    arrayOfDouble[0] = ((b(t) + b(u) * d1 + b(v) * d2) / 206264.80624709636D);
    arrayOfDouble[1] = ((b(w) + b(x) * d1) / 206264.80624709636D);
    arrayOfDouble[2] = ((b(y) + b(z) * d1) * 0.999999949827D);
    arrayOfDouble[0] = (arrayOfDouble[0] + A[0] + A[1] * d1 + A[2] * d2 + A[3] * d3 + A[4] * (d3 * d1));
    arrayOfDouble[0] = c(arrayOfDouble[0]);
    a(paramDouble, arrayOfDouble);
    return arrayOfDouble;
  }
  
  private static String i(double paramDouble)
  {
    int i1 = (int)paramDouble;
    if (paramDouble < 10.0D)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("0");
      localStringBuilder.append(i1);
      return localStringBuilder.toString();
    }
    return String.valueOf(i1);
  }
  
  private static class a
  {
    private double a;
    private double b;
  }
  
  private static class b
  {
    private double a;
    private double b;
    private double c;
    private double d;
    private double e;
    private double f;
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(h.a(this.a));
      localStringBuilder.append(h.a(this.b));
      localStringBuilder.append(h.a(this.c));
      return localStringBuilder.toString();
    }
  }
}


/* Location:              C:\Users\lixia\Desktop\android反编译三件套\dex2jar-2.0\classes-dex2jar.jar!\com\totoro\school\view\calendar\base\h.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */