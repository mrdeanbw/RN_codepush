package com.awesomeproject;

import android.app.Application;
import android.content.Context;
import com.facebook.react.PackageList;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.soloader.SoLoader;
import com.awesomeproject.newarchitecture.MainApplicationReactNativeHost;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import com.microsoft.codepush.react.CodePush;
import java.util.Arrays;
import com.facebook.react.shell.MainReactPackage;

public class MainApplication extends Application implements ReactApplication {

  private final ReactNativeHost mReactNativeHost =
      new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
          return BuildConfig.DEBUG;
        }
        // origin

//         @Override
//         protected List<ReactPackage> getPackages() {
//           @SuppressWarnings("UnnecessaryLocalVariable")
//           List<ReactPackage> packages = new PackageList(this).getPackages();
//           // Packages that cannot be autolinked yet can be added manually here, for example:
//           // packages.add(new MyReactNativePackage());
//           return packages;
//         }

        //  @Override
        //  protected List<ReactPackage> getPackages() {
        //      android.util.Log.d("greeeeeeee",  getResources().getString(R.string.reactNativeCodePush_androidServerURL));
        //    @SuppressWarnings("UnnecessaryLocalVariable")
        //    List<ReactPackage> packages = new PackageList(this).getPackages();
        //    // Packages that cannot be autolinked yet can be added manually here, for example:
        //       //packages.add(new MyReactNativePackage());
        //       packages.add(
        //         new CodePush(
        //          getResources().getString(R.string.reactNativeCodePush_androidDeploymentKey),
        //          getApplicationContext(), BuildConfig.DEBUG,
        //          getResources().getString(R.string.reactNativeCodePush_androidServerURL)
        //        )
        //       );
        //    return packages;
        //  }

        @Override
        protected List<ReactPackage> getPackages() {
            return Arrays.<ReactPackage>asList(
                new MainReactPackage(),
                new CodePush(
                  "Uzjv3My9m0gtjyGNrqrCfWPOgv514ksvOXqog",
                  MainApplication.this,
                  BuildConfig.DEBUG,
                  "http://192.168.1.100:3000"
                  )
            );
        }

//        @Override
//        protected List<ReactPackage> getPackages() {
//            android.util.Log.d("greeeeeeee",  getResources().getString(R.string.reactNativeCodePush_androidServerURL));
//          return Arrays.<ReactPackage>asList(
//              new MainReactPackage(),
//              new CodePush(
//                getResources().getString(R.string.reactNativeCodePush_androidDeploymentKey),
//                getApplicationContext(), BuildConfig.DEBUG,
//                getResources().getString(R.string.reactNativeCodePush_androidServerURL)
//              )
//          );
//        }

        @Override
        protected String getJSMainModuleName() {
          return "index";
        }
        @Override
        protected String getJSBundleFile() {
            return CodePush.getJSBundleFile();
        }
      };

  private final ReactNativeHost mNewArchitectureNativeHost =
      new MainApplicationReactNativeHost(this);

  @Override
  public ReactNativeHost getReactNativeHost() {
    if (BuildConfig.IS_NEW_ARCHITECTURE_ENABLED) {
      return mNewArchitectureNativeHost;
    } else {
      return mReactNativeHost;
    }
  }

  @Override
  public void onCreate() {
    super.onCreate();
    // If you opted-in for the New Architecture, we enable the TurboModule system
    ReactFeatureFlags.useTurboModules = BuildConfig.IS_NEW_ARCHITECTURE_ENABLED;
    SoLoader.init(this, /* native exopackage */ false);
    initializeFlipper(this, getReactNativeHost().getReactInstanceManager());
  }

  /**
   * Loads Flipper in React Native templates. Call this in the onCreate method with something like
   * initializeFlipper(this, getReactNativeHost().getReactInstanceManager());
   *
   * @param context
   * @param reactInstanceManager
   */
  private static void initializeFlipper(
      Context context, ReactInstanceManager reactInstanceManager) {
    if (BuildConfig.DEBUG) {
      try {
        /*
         We use reflection here to pick up the class that initializes Flipper,
        since Flipper library is not available in release mode
        */
        Class<?> aClass = Class.forName("com.awesomeproject.ReactNativeFlipper");
        aClass
            .getMethod("initializeFlipper", Context.class, ReactInstanceManager.class)
            .invoke(null, context, reactInstanceManager);
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      } catch (NoSuchMethodException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        e.printStackTrace();
      }
    }
  }
}
