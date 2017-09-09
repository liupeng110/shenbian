//
//  CHBasePresentViewController.m
//  shenbianapp
//
//  Created by book on 2017/8/20.
//  Copyright © 2017年 杨绍智. All rights reserved.
//

#import "CHBasePresentViewController.h"

@interface CHBasePresentViewController ()

@end

@implementation CHBasePresentViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    
    [self.view addSubview:self.closeBtn];
    [self.closeBtn mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.view).offset(25);
        make.left.equalTo(self.view).offset(15 );
        make.width.height.mas_equalTo(40);
    }];
    
    [self.view addSubview:self.rightTopButton];
    [self.rightTopButton mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.view).offset(20);
        make.right.equalTo(self.view).offset(-20);;
        make.width.height.mas_equalTo(40);
    }];
}


-(LXButton *)closeBtn{
    if (_closeBtn == nil) {
        _closeBtn = [LXButton buttonWithType:(UIButtonTypeCustom)];
        [_closeBtn setImage:[UIImage imageNamed:@"fbwz_gb"] forState:(UIControlStateNormal)];
        [_closeBtn addTarget:self action:@selector(dissMiss) forControlEvents:(UIControlEventTouchDown)];
    }
    return _closeBtn;
}
- (void)dissMiss{
    
    [self dismissViewControllerAnimated:YES completion:nil];
    
}

-(LXButton *)rightTopButton{
    if (_rightTopButton == nil) {
        _rightTopButton = [LXButton buttonWithType:(UIButtonTypeCustom)];
        [_rightTopButton setTitle:@"发布" forState:(UIControlStateNormal)];
        [_rightTopButton setTitleColor:[UIColor redColor] forState:(UIControlStateNormal)];
        [_rightTopButton addTarget:self action:@selector(clickRightTopButton:) forControlEvents:(UIControlEventTouchUpInside)];
    }
    return _rightTopButton;
}

- (void)clickRightTopButton:(UIButton *)button{

    NSException *exception = [NSException exceptionWithName:@"请实现点击方法或者隐藏该按钮" reason:@"父类定义该属性，子类选可自定义该按钮" userInfo:nil];
    [exception raise];
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
