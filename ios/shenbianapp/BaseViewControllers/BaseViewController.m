//
//  BaseViewController.m
//  shenbianapp
//
//  Created by 杨绍智 on 17/7/12.
//  Copyright © 2017 杨绍智. All rights reserved.
//

#import "BaseViewController.h"

@interface BaseViewController ()

@end

@implementation BaseViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.automaticallyAdjustsScrollViewInsets = NO;
    self.view.backgroundColor = [UIColor whiteColor];
    [self.view addSubview:self.navBarView];
    [self bindViewControllerModel];
    [self setupViews];
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
        _navBarView.mhBaseTitleLabel.backgroundColor = [UIColor colorWithHexColor:@"#008E8F"];
    }
    return _navBarView;
    
}

-(void)bindViewControllerModel{

    [RACObserve(self, viewCModel) subscribeNext:^(id x) {
        
        
    }];
    
}

-(void)setupViews{

}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
