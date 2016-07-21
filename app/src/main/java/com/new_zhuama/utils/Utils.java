package com.new_zhuama.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.URLSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.File;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 */
public class Utils {

    public static final String RESULT_CODE_KEY = "resultCode";
    public static final String RESULT_MESSAGE_KEY = "resultMessage";

    public static final int RESULT_CODE_ERROR = 0;
    public static final int RESULT_CODE_SUCCESS = 1;


    public static boolean SEND_RED_IS_SHOW = false;
    public static final int REQUEST_CODE_PAYMENT = 600;
    public static final String FILE_PATH = "/drama";
    public static final String CAMERA = "/camera";
    public static final String REQUEST_CODE = "REQUEST_CODE";//请求的code


    public static final int TV_SHOW_MORE = 1;//显示更多
    public static final int TV_PACK_UP = 2;//收起


//    public static ImageLoaderConfiguration getConfig(Context context) { // 参数
//
//
//        // 当前上下文的对象
//        // 屏幕的宽高
//        File cacheDir = StorageUtils.getOwnCacheDirectory(context, "drama/image"); // 设置缓存位置
//        // 1.完成ImageLoaderConfiguration的配置
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
//                .memoryCacheExtraOptions(480, 800)
//
//                // 即保存每个缓存文件的最大长宽
//                // default = device screen dimensions
//                // .taskExecutor(...)
//                // .taskExecutorForCachedImages(...)
//                .threadPoolSize(5)
//                // default
//                .threadPriority(Thread.NORM_PRIORITY - 2)
//                // default
//                .tasksProcessingOrder(QueueProcessingType.LIFO)
//                // default
//                .denyCacheImageMultipleSizesInMemory()
//                // .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
//                .memoryCache(new WeakMemoryCache()).memoryCacheSize(20 * 1024 * 1024)
//                .memoryCacheSizePercentage(13)
//                // default
//                .diskCache(new UnlimitedDiskCache(cacheDir))
//                // 设置缓存
//                .diskCacheSize(50 * 1024 * 1024)
//                // 缓冲文件数目
//                .diskCacheFileCount(50).diskCacheFileNameGenerator(new Md5FileNameGenerator())
//                // 将保存的时候的URL名称用MD5加密
//                .imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000)) // connectTimeout(5s),
//                // readTimeout
//                // (30s)超时时间
//                .imageDecoder(new BaseImageDecoder(true)) // default
//                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
//                .build();
//        return config;
//    }
//
//
//    public static DisplayImageOptions getOpitons() {
//        // 3.DisplayImageOptions实例对象的配置
//        DisplayImageOptions options = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.mipmap.app_default_icon) // 设置图片在下载期间显示的图片
//                .showImageForEmptyUri(R.mipmap.app_default_icon)// 设置图片Uri为空或是错误的时候显示的图片
//                .showImageOnFail(R.mipmap.app_default_icon) // 设置图片加载/解码过程中错误时候显示的图片
//                .cacheInMemory(true).cacheOnDisk(true).considerExifParams(true) // 是否考虑JPEG图像EXIF参数（旋转，翻转）
//                .bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型//
////                .displayer(new FadeInBitmapDisplayer(600))// 是否图片加载好后渐入的动画时间
//                .build();// 构建完成
//
//        return options;
//    }
//
//
//    /**
//     * 打开图片查看器
//     *
//     * @param position
//     * @param urls2
//     */
//    public static void imageBrower(Context mContext, int position, ArrayList<String> urls2) {
//        Intent intent = new Intent(mContext, ImagePagerActivity.class);
//        intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS, urls2);
//        intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, position);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        mContext.startActivity(intent);
//
//    }


    /**
     * app VersionName 获取
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        String versionName = "";
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }


    /**
     * 隐藏显示
     *
     * @param view
     */
    public static void hiddeSoftKeyBoard(View view) {

        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
        }
//        InputMethodManager iim = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//        iim.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }

    /**
     * 取反
     *
     * @param view
     */
    public static void showSoftKeyBoard(View view) {
        InputMethodManager iim = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        iim.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 强制显示
     *
     * @param v
     */
    public static void ShowKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(v, InputMethodManager.SHOW_FORCED);

    }

    public static void jumpMethod(Context context, Class clazz) {
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
    }

    public static void jumpMethod(Context context, Class clazz, Bundle bundle) {
        Intent intent = new Intent(context, clazz);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static void jumpForResultMethod(Activity context, Class clazz, Bundle bundle) {
        Intent intent = new Intent(context, clazz);
        intent.putExtras(bundle);
        int code = bundle.getInt(REQUEST_CODE, 0);
        context.startActivityForResult(intent, code);
    }


    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 101;

//    public static String jumForCamera(Activity activity) {
//
////        if (ContextCompat.checkSelfPermission(activity,
////                Manifest.permission.CAMERA)
////                != PackageManager.PERMISSION_GRANTED) {
////            //请求权限
////            ActivityCompat.requestPermissions(activity,
////                    new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
////            //判断是否需要 向用户解释，为什么要申请该权限
////            ActivityCompat.shouldShowRequestPermissionRationale(activity,
////                    Manifest.permission.READ_CONTACTS);
////        } else {
//        String path = Environment.getExternalStorageDirectory().toString()
//                + Utils.FILE_PATH + Utils.CAMERA;
//        File folder = new File(path);
//        if (!folder.exists()) {
//            folder.mkdirs();
//        }
//        String pathName = System.currentTimeMillis() + ".jpg";
//        File file = new File(path, pathName);
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
//        activity.startActivityForResult(intent, CreateNoteOneFragment.REQUEST_CODE_CAMERA);
//
//        return path + "/" + pathName;
////        }
////        return null;
//
//    }

    public static void galleryAddPic(String pathName, Context context) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(pathName);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        context.sendBroadcast(mediaScanIntent);
    }

    /**
     * ping++支付
     *
     * @param charge
     * @param activity
     */
//    public static void pingxxPlay(String charge, Activity activity) {
//        Intent intent = new Intent();
//        String packageName = activity.getPackageName();
//        ComponentName componentName = new ComponentName(packageName, packageName + ".wxapi.WXPayEntryActivity");
//        intent.setComponent(componentName);
//        intent.putExtra(PaymentActivity.EXTRA_CHARGE, charge);
//        activity.startActivityForResult(intent, REQUEST_CODE_PAYMENT);
//    }

    /**
     * 获取屏幕高宽
     *
     * @param context
     * @return
     */
    public static int[] getScreenWidth(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return new int[]{dm.widthPixels, dm.heightPixels};
    }

    /**
     * 格式化金额
     *
     * @param money
     * @return
     */
    public static String formatMoney(double money) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(money);
    }


    public static SpannableString UrlSpannabString(String conent, int start, int end) {
        SpannableString ss = new SpannableString(conent);
        URLSpan span = new URLSpan("tel:010-84349482");
        ss.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }


//    public static SpannableString setSpannableColor(String content, ArrayList<DynamicTopic> topic, int colorId) {
//        return setSpannableColor(null, content, topic, colorId, false);
//    }
//
//    public static SpannableString setSpannableColor(final Context context, String content, final ArrayList<DynamicTopic> topic, final int colorId, boolean isClick) {
//        SpannableString ss = new SpannableString(content);
//
//        for (int i = 0; i < topic.size(); i++) {
//            ForegroundColorSpan color = new ForegroundColorSpan(colorId);
//            ss.setSpan(color, topic.get(i).getStartIndex(), topic.get(i).getEndIndex() + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//            if (isClick) {
//                final int finalI = i;
//                NoLineCllikcSpan click = new NoLineCllikcSpan() {
//                    @Override
//                    public void onClick(View widget) {
//
//                        if (widget instanceof ClickPreventableTextView) {
//                            ((ClickPreventableTextView) widget).linkHit = true;
//                        }
//                        Bundle bundle = new Bundle();
//                        bundle.putString("where", topic.get(finalI).getTopicContent());
//                        FragmentUtils.navigateToInNewBackActivity(context, DynamicFragment.class
//                                , bundle);
//                    }
//
//                    @Override
//                    public void updateDrawState(TextPaint ds) {
//                        super.updateDrawState(ds);
//                        ds.setColor(colorId);
//                    }
//                };
//
//                ss.setSpan(click, topic.get(i).getStartIndex(), topic.get(i).getEndIndex() + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//            }
//
//        }
//
//        return ss;
//    }
//
//
//    public static BDLocation mBdLocation;
//
//    public static void getLocation(Activity activity) {
//        final LocationClient client = new LocationClient(activity);
//
//        LocationClientOption option = new LocationClientOption();
//        option.setOpenGps(true);//设置大概gps
//        option.setCoorType("gcj02");//gcj02   bd09ll
//        option.setPriority(LocationClientOption.NetWorkFirst);
//        option.setScanSpan(1000);
//        option.setIsNeedAddress(true);//反编译获得具体位置，只有网络定位才可以
//        client.setLocOption(option);
//
//        client.registerLocationListener(new BDLocationListener() {
//            @Override
//            public void onReceiveLocation(BDLocation bdLocation) {
//
//                if (bdLocation == null) return;
//                mBdLocation = bdLocation;
//
//                Log.v("TAG", bdLocation.getLatitude() + "  " + bdLocation.getLongitude() + "  " + bdLocation.getAddrStr());
//                client.stop();
//            }
//        });
//
//        if (!client.isStarted()) {
//            client.start();
//        }
//
//    }
//
//    public static void getLocation(Activity activity, BDLocationListener listener, LocationClient client) {
//
//        LocationClientOption option = new LocationClientOption();
//        option.setOpenGps(true);//设置大概gps
//        option.setCoorType("gcj02");
//        option.setPriority(LocationClientOption.NetWorkFirst);
//        option.setScanSpan(1000);
//        option.setIsNeedAddress(true);//反编译获得具体位置，只有网络定位才可以
//        client.setLocOption(option);
//
//        client.registerLocationListener(listener);
//
//        if (!client.isStarted()) {
//            client.start();
//        }
//
//    }
//
//    /**
//     * 获取本地创建地址缓存
//     */
//    public static HashMap<String, ArrayList<LocationEntity>> getUserLocationCach(String cach) {
//
//        HashMap<String, ArrayList<LocationEntity>> map = new HashMap<>();
//        try {
//            JSONObject object = new JSONObject(cach);
//
//            Iterator<String> keys = object.keys();
//
//            String key;
//            String value;
//            Type type;
//
//            while (keys.hasNext()) {
//
//                key = keys.next();
//
//                value = object.get(key).toString();
//
//                type = new TypeToken<ArrayList<LocationEntity>>() {
//                }.getType();
//
//                ArrayList<LocationEntity> entities = new Gson().fromJson(value, type);
//
//                map.put(key, entities);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return map;
//    }
//
//
//    /**
//     * 获取话题集合
//     *
//     * @param message 内容
//     * @return
//     */
//    public static ArrayList<DynamicTopic> getDynamicTopic(String message) {
//
//        ArrayList<DynamicTopic> dynamicTopics = new ArrayList<>();
//
//        String oldMessage = message;
//
//        while (message.indexOf("#") > -1) {//寻找#
//            int startIndex = message.indexOf("#");//#开始的位置
//            if (startIndex > -1) {//判断是否有#
//                int endIndex = message.indexOf("#", startIndex + 1);//查找#之后是否有#
//                if (endIndex > -1) {
//
//                    String conent = message.substring(startIndex, endIndex + 1);//截取话题内容
//                    Pattern pattern = Pattern.compile("^#(?!\\s*#)[^#]+#$");
//                    if (pattern.matcher(conent).matches()) {//看是否匹配正则
//
//                        int distance = oldMessage.length() - message.length();//计算它的下标
//
//                        DynamicTopic dt = new DynamicTopic();//把话题信息存起来
//                        dt.setStartIndex(startIndex + distance);
//                        dt.setEndIndex(endIndex + distance);
//                        dt.setTopicContent(conent);
//                        dynamicTopics.add(dt);
//                        endIndex += 1;//从最后一个#的位置+1开始重新找
//                    }
//                    message = message.substring(endIndex, message.length());//截取话题之后的内容
//                } else {
//                    break;
//                }
//            } else {
//                break;
//            }
//        }
//        return dynamicTopics;
//    }


    /**
     * bd_encrypt 将 GCJ-02 坐标转换成 BD-09 坐标
     *
     * @param gg_lat
     * @param gg_lon
     * @return
     */
    public static double[] bd_encrypt(double gg_lat, double gg_lon) {
        double x_pi = 3.14159265358979324 * 3000.0 / 180.0;
        double x = gg_lon, y = gg_lat;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
        double bd_lon = z * Math.cos(theta) + 0.0065;
        double bd_lat = z * Math.sin(theta) + 0.006;
        return new double[]{bd_lat, bd_lon};
    }

    /**
     * bd_decrypt 将 BD-09 坐标转换成 GCJ-02 坐标
     *
     * @param bd_lat
     * @param bd_lon
     * @return
     */
    public static double[] bd_decrypt(double bd_lat, double bd_lon) {
        double x_pi = 3.14159265358979324 * 3000.0 / 180.0;
        double x = bd_lon - 0.0065, y = bd_lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
        double gg_lon = z * Math.cos(theta);
        double gg_lat = z * Math.sin(theta);
        return new double[]{gg_lat, gg_lon};
    }

    public static String ToSBC(String input) {
        // 半角转全角：
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 32) {
                c[i] = (char) 12288;
                continue;
            }
            if (c[i] < 127)
                c[i] = (char) (c[i] + 65248);
        }
        return new String(c);
    }

    /**
     * 全角转换为半角
     *
     * @param input
     * @return
     */
    public static String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    //要判断是否包含特殊字符的目标字符串
    public static boolean compileExChar(String str, int start, int end, boolean isNum) {
        StringBuilder sb = new StringBuilder("^[ a-zA-Z\\u4e00-\\u9fa5");
        sb.append("\\u1100-\\u11ff\\uac00-\\ud7af\\u3130–\\u318F\\u3200–\\u32FF\\uA960–\\uA97F\\uD7B0–\\uD7FF\\uFF00–\\uFFEF");
        if (isNum) {
            sb.append("0-9]");
        } else {
            sb.append("]");
        }
        sb.append("{" + start + "," + end + "}$");
//        String regEx = "^[ a-zA-Z\\u4e00-\\u9fa5" +
//                "\\u1100-\\u11ff\\uac00-\\ud7af\\u3130–\\u318F\\u3200–\\u32FF\\uA960–\\uA97F\\uD7B0–\\uD7FF\\uFF00–\\uFFEF]" +
//                "{" + start + "," + end + "}$";
        Pattern p = Pattern.compile(sb.toString());
        Matcher m = p.matcher(str);
        boolean isSure = m.matches();
        return isSure;
    }


//    public static User getUser(Context context) {
//        String userJson = MySharedPreferences.getInstance(context).getLoginUser();
//
//        if (StringUtils.isEmpty(userJson)) {
//            return null;
//        } else {
//            User user = new Gson().fromJson(userJson, User.class);
//            return user;
//        }
//    }

    public static int[] getDay(String time) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar now = Calendar.getInstance();
        now.setTime(date);

        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1; // 0-based!
        int day = now.get(Calendar.DAY_OF_MONTH);

//        System.out.println("year: " + year);
//        System.out.println("month: " + month);
//        System.out.println("day: " + day);

        return new int[]{year,month,day};
    }

}
