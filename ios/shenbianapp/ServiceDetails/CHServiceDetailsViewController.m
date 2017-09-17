//
//  CHServiceDetailsViewController.m
//  shenbianapp
//
//  Created by book on 2017/9/12.
//  Copyright © 2017年 陈坚. All rights reserved.
//

#import "CHServiceDetailsViewController.h"

@interface CHServiceDetailsViewController ()
@property(nonatomic,strong) CHServiceUperView * topView;

@property(nonatomic,strong)CHServiceMiddleView *middleView;
@property(nonatomic,strong)CHServiceBottomView *bottomView;

@end

@implementation CHServiceDetailsViewController


- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    
    [self.view addSubview:self.topView];
    [self.topView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.top.right.equalTo(self.view);
        make.height.mas_equalTo(230);
    }];
    
    [self.topView addSubview:self.backButton];
    [self.backButton mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(self.topView).offset(8);
        make.top.equalTo(self.topView).offset(20);
        make.width.mas_equalTo(40);
        make.height.mas_equalTo(40);
    }];
    [self.backButton setImage:[UIImage imageNamed:@"ydwz_fh"] forState:(UIControlStateNormal)];

    
    [self.view addSubview:self.bottomView];
    [self.bottomView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.bottom.equalTo(self.view);
        make.height.mas_equalTo(150);
    }];
    
    
    [self.view addSubview:self.middleView];
    [self.middleView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.equalTo(self.view);
        make.top.equalTo(self.topView.mas_bottom);
        make.bottom.equalTo(self.bottomView.mas_top);
    }];
}


-(UIView *)topView{
    if (_topView == nil) {
        _topView = [CHServiceUperView new];
        _topView.backgroundColor = [UIColor colorWithHexString:@"#f9ee82"];
    }
    return _topView;
}

-(CHServiceMiddleView *)middleView{
    if (_middleView == nil) {
        _middleView = [CHServiceMiddleView new];
    }
    return _middleView;
}

-(CHServiceBottomView *)bottomView{
    if (_bottomView == nil) {
        _bottomView = [CHServiceBottomView new];
    }
    return _bottomView;
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
