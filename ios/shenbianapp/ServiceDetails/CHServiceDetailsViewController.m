//
//  CHServiceDetailsViewController.m
//  shenbianapp
//
//  Created by book on 2017/9/12.
//  Copyright © 2017年 陈坚. All rights reserved.
//

#import "CHServiceDetailsViewController.h"

@interface CHServiceDetailsViewController ()
@property(nonatomic,strong)UIButton *collectButton;
@property(nonatomic,strong) UIView * topView;
@property(nonatomic,strong)UIButton *topShareButton;
@property(nonatomic,strong)UIImageView *introduceImageView;
@end

@implementation CHServiceDetailsViewController
//@synthesize  backButton =_backButton;
- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.

    [self.view addSubview:self.topView];
    [self.topView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.top.right.equalTo(self.view);
        make.height.mas_equalTo(230);
    }];
    [self.topView addSubview:self.backButton];
    [self.backButton setImage:[UIImage imageNamed:@"ydwz_fh"] forState:(UIControlStateNormal)];

    [self.backButton mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(self.topView).offset(8);
        make.top.equalTo(self.topView).offset(20);
        make.width.mas_equalTo(40);
        make.height.mas_equalTo(40);
    }];
    
    [self.topView addSubview:self.topShareButton];
    [self.topShareButton mas_makeConstraints:^(MASConstraintMaker *make) {
        make.right.equalTo(self.topView).offset(-15);
        make.top.equalTo(self.topView).offset(20);
        make.width.mas_equalTo(40);
        make.height.mas_equalTo(40);
    }];
    
    [self.topView addSubview:self.collectButton];
    [self.collectButton mas_makeConstraints:^(MASConstraintMaker *make) {
        make.right.equalTo(self.topShareButton.mas_left).offset(-15);
        make.top.equalTo(self.topView).offset(20);
        make.width.mas_equalTo(40);
        make.height.mas_equalTo(40);
    }];
    
    [self.topView addSubview:self.introduceImageView];
    [self.introduceImageView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.centerX.equalTo(self.topView);
        make.bottom.equalTo(self.topView);
        make.width.mas_equalTo(216);
        make.height.mas_equalTo(144);
    }];
}

-(UIView *)topView{

    if (_topView == nil) {
        _topView = [UIView new];
        _topView.backgroundColor = [UIColor colorWithHexString:@"#f9ee82"];
    }
    return _topView;
}

-(UIButton *)topShareButton{
    if (_topShareButton == nil) {
        _topShareButton = [UIButton buttonWithType:(UIButtonTypeCustom)];
        [_topShareButton setImage:[UIImage imageNamed:@"ydwz_zf"] forState:(UIControlStateNormal)];
        [_topShareButton addTarget:self action:@selector(clickShareButton) forControlEvents:(UIControlEventTouchUpInside)];
    }
    
    return _topShareButton;
}

-(void)clickShareButton{

}

-(UIButton *)collectButton{
    if (_collectButton == nil) {
        _collectButton = [UIButton buttonWithType:(UIButtonTypeCustom)];
        _collectButton.frame = CGRectMake(0, 0, 40, 40);
        [_collectButton setImage:[UIImage imageNamed:@"ydwz_sc"] forState:UIControlStateNormal];
    }
    return _collectButton;
}

-(UIImageView *)introduceImageView{

    if (_introduceImageView == nil) {
        _introduceImageView = [[UIImageView alloc]initWithImage:[UIImage imageNamed:@"sy_sj_cover"]];
    }
    return _introduceImageView;

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
