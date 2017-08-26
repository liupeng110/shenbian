//
//  BaseViewController.m
//  shenbianapp
//
//  Created by 杨绍智 on 17/7/12.
//  Copyright © 2017年 杨绍智. All rights reserved.
//

#import "BaseViewController.h"

@interface BaseViewController ()

@end

@implementation BaseViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.automaticallyAdjustsScrollViewInsets = NO;
    self.view.backgroundColor = [UIColor whiteColor];
    //[[UIApplication sharedApplication] setStatusBarStyle:UIStatusBarStyleLightContent];
    [self.view addSubview:self.navBarView];
}

- (UIStatusBarStyle)preferredStatusBarStyle{
    return UIStatusBarStyleLightContent;
}
- (MHBaseNavigationBar *)navBarView{
    if (nil == _navBarView) {
        _navBarView = [[[NSBundle mainBundle]loadNibNamed:@"MHBaseNavigationBar" owner:self options:nil] firstObject];
        //_navBarView.delegate = self;
        _navBarView.backgroundColor = [UIColor colorWithHexColor:@"#008E8F"];
        _navBarView.frame = CGRectMake(0, 0, kScreenWidth, 64);
    }
    
    return _navBarView;
    
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
