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
@synthesize backButton,rightButton;

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.view.backgroundColor = [UIColor whiteColor];
    [[UINavigationBar appearance] setTintColor:[UIColor whiteColor]];
    [[UINavigationBar appearance] setBarTintColor:[UIColor colorWithHexColor:@"#009698"]];
    [self.navigationController.navigationBar setTitleTextAttributes:[NSDictionary dictionaryWithObjectsAndKeys:[UIColor whiteColor],NSForegroundColorAttributeName,[UIFont systemFontOfSize:17],NSFontAttributeName,nil]];

    self.navigationItem.backBarButtonItem.title = @"";
     backButton = [UIButton buttonWithType:(UIButtonTypeCustom)];
    [backButton setImage:[UIImage imageNamed:@"tx_fh"] forState:(UIControlStateNormal)];
    [backButton addTarget:self action:@selector(clickBackButton) forControlEvents:(UIControlEventTouchUpInside)];
    backButton.imageEdgeInsets = UIEdgeInsetsMake(0, -20, 0, 0);
    backButton.frame = CGRectMake(0, 0, 40, 40);
    self.navigationItem.leftBarButtonItem = [[UIBarButtonItem alloc]initWithCustomView:backButton];
    
    rightButton = [UIButton buttonWithType:(UIButtonTypeCustom)];
    [rightButton addTarget:self action:@selector(clickRightButton) forControlEvents:(UIControlEventTouchUpInside)];
    [rightButton.titleLabel setFont:[UIFont systemFontOfSize:15]];
    rightButton.frame = CGRectMake(0, 0, 40, 40);
    self.navigationItem.rightBarButtonItem = [[UIBarButtonItem alloc]initWithCustomView:rightButton];
    self.rightButton.hidden = YES;
}

- (void)clickBackButton{
    [self.navigationController popViewControllerAnimated:YES];
}

- (void) clickRightButton{

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
