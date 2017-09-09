//
//  CHBaseNavgationViewController.m
//  shenbianapp
//
//  Created by book on 2017/8/20.
//  Copyright © 2017年 杨绍智. All rights reserved.
//

#import "CHBaseNavgationViewController.h"

@interface CHBaseNavgationViewController ()


@end

@implementation CHBaseNavgationViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [[UIBarButtonItem appearance] setBackButtonTitlePositionAdjustment:UIOffsetMake(0, -60) forBarMetrics:UIBarMetricsDefault];
    self.navigationItem.backBarButtonItem.title = @"";
    
    [self.view addSubview:self.backButton];
    [self.backButton mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(self.view).offset(15);
        make.top.equalTo(self.view).offset(40);
        make.width.mas_equalTo(10);
        make.height.mas_equalTo(18);
    }];
}

-(UIButton *)backButton{
    if (_backButton == nil) {
        _backButton = [UIButton buttonWithType:(UIButtonTypeCustom)];
        [_backButton  setImage:[UIImage imageNamed:@"tx_fh"] forState:(UIControlStateNormal)];
        [_backButton addTarget:self action:@selector(clickBackButton) forControlEvents:(UIControlEventTouchUpInside)];
    }
    return _backButton;
}

- (void)clickBackButton{

    [self dismissViewControllerAnimated:YES completion:nil];

}
- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
