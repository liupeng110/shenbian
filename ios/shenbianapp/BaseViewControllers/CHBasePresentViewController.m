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
    
    [self.view addSubview:self.topView];
    [self.topView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.top.right.equalTo(self.view);
        make.height.mas_equalTo(64);
    }];
    
    [self.topView addSubview:self.lefgtButton];
    [self.lefgtButton mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.topView).offset(22);
        make.left.equalTo(self.topView).offset(6);
        make.width.height.mas_equalTo(40);
    }];
    
    [self.topView addSubview:self.rightTopButton];
    [self.rightTopButton mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.topView).offset(20);
        make.right.equalTo(self.topView).offset(-20);;
        make.width.height.mas_equalTo(40);
    }];
    self.navBarView.hidden = YES;
}

-(UIView *)topView{
    if (_topView == nil) {
        _topView = [UIView new];
        _topView.backgroundColor = [UIColor whiteColor];
    }
    return _topView;
}

-(LXButton *)lefgtButton{
    if (_lefgtButton == nil) {
        _lefgtButton = [LXButton buttonWithType:(UIButtonTypeCustom)];
        [_lefgtButton setImage:[UIImage imageNamed:@"fbwz_gb"] forState:(UIControlStateNormal)];
        [_lefgtButton addTarget:self action:@selector(dissMiss) forControlEvents:(UIControlEventTouchDown)];
    }
    return _lefgtButton;
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
