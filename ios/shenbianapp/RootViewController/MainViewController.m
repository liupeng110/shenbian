//
//  MainViewController.m
//  shenbianapp
//
//  Created by   on 17/7/12.
//  Copyright © 2017  . All rights reserved.
//

#import "MainViewController.h"
#import "RootHeaderViewController.h"
#import "CHDiscoverViewController.h"
#import "RootMineViewController.h"
#import "HQTabBar.h"
#import "CHPublishViewController.h"
#import "CHLoginViewController.h"
#import "CHMessageViewController.h"
@interface MainViewController ()<HQTabBardelegate>

@end

@implementation MainViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = [UIColor whiteColor];
    [self addChildVc:[[RootHeaderViewController alloc]init] title:@"首页" image:@"sy_syb" selectedImage:@"sy_sya"];
    
    [self addChildVc:[[CHMessageViewController alloc] init] title:@"消息" image:@"sy_gzb" selectedImage:@"sy_gza"];
    
    [self addChildVc:[[CHDiscoverViewController alloc] init] title:@"发现" image:@"sy_fxb" selectedImage:@"sy_fxa"];
    
    [self addChildVc:[[RootMineViewController alloc] init] title:@"我的" image:@"sy_wdb" selectedImage:@"sy_wda"];
    
    [self creatHQTabBar];//中间➕号
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(startLogin) name:kCHNotificationLogin object:nil];
}

- (void)addChildVc:(UIViewController *)childVc title:(NSString *)title image:(NSString *)image selectedImage:(NSString *)selectedImage
{
    childVc.title = title;
    //childVc.tabBarItem.title = title;
    //[childVc.tabBarItem setTitlePositionAdjustment:UIOffsetMake(0, -2)];
    // 禁用图片渲染
    childVc.tabBarItem.selectedImage = [[UIImage imageNamed:selectedImage] imageWithRenderingMode:UIImageRenderingModeAlwaysOriginal];
    childVc.tabBarItem.image = [[UIImage imageNamed:image] imageWithRenderingMode:UIImageRenderingModeAlwaysOriginal];
    //childVc.tabBarItem.imageInsets = UIEdgeInsetsMake(-2, 0, 0, 0);
    // 设置文字的样式
    
    //[childVc.tabBarItem  setTitleTextAttributes:[NSDictionary dictionaryWithObjectsAndKeys:[UIColor lightGrayColor], NSForegroundColorAttributeName, [UIFont systemFontOfSize:11.0f],NSFontAttributeName,nil] forState:UIControlStateNormal];
    //[childVc.tabBarItem  setTitleTextAttributes:[NSDictionary dictionaryWithObjectsAndKeys:[UIColor blueColor], NSForegroundColorAttributeName, [UIFont systemFontOfSize:11.0f],NSFontAttributeName,nil] forState:UIControlStateSelected];
    UINavigationController *nav = [[UINavigationController alloc] initWithRootViewController:childVc];
    nav.navigationBarHidden = YES;
    [self addChildViewController:nav];
    
}

#pragma mark 创建tabbar
- (void)creatHQTabBar{
    HQTabBar *tabBar = [[HQTabBar alloc] init];
    tabBar.delegate = self;
    //    //去掉tabBar上黑线
    //    CGRect rect = CGRectMake(0, 0, ScreenWidth, ScreenHeight);
    //    UIGraphicsBeginImageContext(rect.size);
    //    CGContextRef context = UIGraphicsGetCurrentContext();
    //    CGContextSetFillColorWithColor(context, [[UIColor clearColor] CGColor]);
    //    CGContextFillRect(context, rect);
    //    UIImage *img = UIGraphicsGetImageFromCurrentImageContext();
    //    UIGraphicsEndImageContext();
    //    [tabBar setBackgroundImage:img];
    //    [tabBar setShadowImage:img];
    // KVC：如果要修系统的某些属性，但被设为readOnly，就是用KVC，即setValue：forKey：。
    [self setValue:tabBar forKey:@"tabBar"];
    //    UIImageView *bagImgView = [[UIImageView alloc]initWithFrame:CGRectMake(0, 0, ScreenWidth, 61)];
    //    if (IPHONE4S || IPHONE5S) {
    //        bagImgView.image = [UIImage imageNamed:@"bottomTabar.png"];
    //    }else{
    //        bagImgView.image = [SVGKImage imageNamed:@"1.5vdibudanghanglan.svg"].UIImage;
    //    }
    //    self.tabBar.barTintColor = [UIColor clearColor];
    //    [self.tabBar insertSubview:bagImgView atIndex:0];
    // self.tabBar.opaque = YES;
    [[UITabBar appearance] setBackgroundColor:[UIColor whiteColor]];
//    self.tabBar.barTintColor = [UIColor blackColor];
}

-(void)TabBarDidClickPlusButton:(HQTabBar *)tabBar{

    //判断登录
    BOOL login = [[NSUserDefaults standardUserDefaults] boolForKey:@"login"];
//    login = YES;
    if (login) {

        CHPublishViewController *publish = [[CHPublishViewController alloc]init];
        UINavigationController *nav = [[UINavigationController alloc]initWithRootViewController:publish];
        nav.modalPresentationStyle = UIModalPresentationOverCurrentContext;
        [self presentViewController:nav animated:YES completion:nil];
    } else {
        [self startLogin];
    }

}

-(void)startLogin{
    CHLoginViewController *logoinVC = [[CHLoginViewController alloc]init];
    UINavigationController *nav = [[UINavigationController alloc]initWithRootViewController:logoinVC];
    
    [self presentViewController:nav animated:YES completion:nil];
    
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
