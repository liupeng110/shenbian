//
//  MainViewController.m
//  shenbianapp
//
//  Created by 杨绍智 on 17/7/12.
//  Copyright © 2017年 杨绍智. All rights reserved.
//

#import "MainViewController.h"
#import "RootHeaderViewController.h"
#import "RootFocusViewController.h"
#import "RootFindeViewController.h"
#import "RootMineViewController.h"
@interface MainViewController ()

@end

@implementation MainViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = [UIColor whiteColor];
    [self addChildVc:[[RootHeaderViewController alloc]init] title:@"首页" image:@"2.4guanzhu1" selectedImage:@"2.4guanzhu1"];
    
    [self addChildVc:[[RootFocusViewController alloc] init] title:@"关注" image:@"2.4xiaoxi1" selectedImage:@"2.4xiaoxi1"];
    
    [self addChildVc:[[RootFindeViewController alloc] init] title:@"发现" image:@"2.4faxian1" selectedImage:@"2.4faxian1"];
    
    [self addChildVc:[[RootMineViewController alloc] init] title:@"我的" image:@"2.4wode1" selectedImage:@"2.4wode1"];
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

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
