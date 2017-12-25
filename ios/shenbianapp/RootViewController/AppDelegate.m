//
//  AppDelegate.m
//  shenbianapp
//
//  Created by 杨绍智 on 17/7/12.
//  Copyright © 2017 杨绍智. All rights reserved.
//

#import "AppDelegate.h"
#import "MainViewController.h"
#import <AMapFoundationKit/AMapFoundationKit.h>

#import <RongIMLib/RongIMLib.h>
#import <RongIMKit/RongIMKit.h>

#import <WXApi.h>
@interface AppDelegate ()<WXApiDelegate>

@end

@implementation AppDelegate


- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {
    self.window = [[UIWindow alloc] init];
    self.window.frame = [UIScreen mainScreen].bounds;
    self.window.backgroundColor = [UIColor whiteColor];
    MainViewController * main = [[MainViewController alloc]init];
    self.window.rootViewController = main;
    [self.window makeKeyAndVisible];
    [[AMapServices sharedServices] setEnableHTTPS:YES];
    [AMapServices sharedServices].apiKey = @"0dc1126a57b00e8babb475778cd99e81";
    [[RCIM sharedRCIM] initWithAppKey:@"mgb7ka1nmwffg"];
    [[RCIM sharedRCIM] setScheme:@"userviceRedPacket" forExtensionModule:@"JrmfPacketManager"];
    [[RCIM sharedRCIM] connectWithToken:@"lZxlQOonERbH4OZlaoGCskfahqlL3dhxC0IyB1eTNaLD5DUa4755NDXSK1BuVqVEcoyQ3XWiZ83lPu8m+jj4uw==" success:^(NSString *userId) {
        
    } error:^(RCConnectErrorCode status) {
        
    } tokenIncorrect:^{
        
    }];
    [WXApi registerApp:@"wx647da34106a75ac2" ];
    [[IQKeyboardManager sharedManager] setEnable:NO];
    [IQKeyboardManager sharedManager].enableAutoToolbar = YES;
    [IQKeyboardManager sharedManager].toolbarDoneBarButtonItemText = @"完成";
    [IQKeyboardManager sharedManager].toolbarTintColor = [UIColor redColor];
    return YES;
}


- (void)applicationWillResignActive:(UIApplication *)application {
    // Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
    // Use this method to pause ongoing tasks, disable timers, and invalidate graphics rendering callbacks. Games should use this method to pause the game.
}


- (void)applicationDidEnterBackground:(UIApplication *)application {
    // Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later.
    // If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
}


- (void)applicationWillEnterForeground:(UIApplication *)application {
    // Called as part of the transition from the background to the active state; here you can undo many of the changes made on entering the background.
}


- (void)applicationDidBecomeActive:(UIApplication *)application {
    // Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
}


- (void)applicationWillTerminate:(UIApplication *)application {
    // Called when the application is about to terminate. Save data if appropriate. See also applicationDidEnterBackground:.
}

- (BOOL)application:(UIApplication *)app openURL:(NSURL *)url options:(NSDictionary<NSString *,id> *)options {
    if ([[RCIM sharedRCIM] openExtensionModuleUrl:url]) {
        return YES;
    }
    
    return [WXApi handleOpenURL:url delegate:self];
}

- (BOOL)application:(UIApplication *)application openURL:(NSURL *)url sourceApplication:(NSString *)sourceApplication annotation:(id)annotation {
    if ([[RCIM sharedRCIM] openExtensionModuleUrl:url]) {
        return YES;
    }
    return [WXApi handleOpenURL:url delegate:self];
}

-(BOOL)application:(UIApplication *)application handleOpenURL:(NSURL *)url{
    
    return [WXApi handleOpenURL:url delegate:self];
}

-(void)onReq:(BaseReq *)req{
    
}

-(void)onResp:(BaseResp *)resp{
    if ([resp isKindOfClass:[PayResp class]]){
        PayResp*response=(PayResp*)resp;
        switch(response.errCode){
            case WXSuccess:{
                //服务器端查询支付通知或查询API返回的结果再提示成功
                NSLog(@"支付成功");
                UIAlertView *alert = [[UIAlertView alloc]initWithTitle:@"支付提示" message:@"订单支付成功" delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil, nil];
                [alert show];
                [[NSNotificationCenter defaultCenter] postNotificationName:kCHNotificationWXPaySuccess object:nil];
            }
                break;
            default:
                NSLog(@"支付失败，retcode=%d",resp.errCode);
                break;
        }
    }
    
}


@end
