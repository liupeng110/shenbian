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
@property(nonatomic,strong)UIButton *topBackButton;
@end

@implementation CHServiceDetailsViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.navigationController.navigationBarHidden = YES;
}

-(void)bindViewControllerModel{

}

-(void)setupViews{

    [self.view addSubview:self.topView];
    [self.topView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.top.right.equalTo(self.view);
        make.height.mas_equalTo(220);
    }];
    [self.topBackButton removeFromSuperview];
    [self.view addSubview:self.topBackButton];
    [self.topBackButton mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(self.topView).offset(8);
        make.top.equalTo(self.topView).offset(20);
        make.width.mas_equalTo(40);
        make.height.mas_equalTo(40);
    }];
    
    [self.view addSubview:self.bottomView];
    [self.bottomView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.equalTo(self.view);
        make.bottom.equalTo(self.view);
        make.height.mas_equalTo(55);
    }];
    
    [self.view addSubview:self.middleView];
    [self.middleView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.equalTo(self.view);
        make.top.equalTo(self.topView.mas_bottom).offset(10);
        make.bottom.equalTo(self.bottomView.mas_top);
    }];

}

-(UIButton *)topBackButton{
    if (_topBackButton == nil) {
        _topBackButton = [UIButton buttonWithType:(UIButtonTypeCustom)];
        [_topBackButton setImage:[UIImage imageNamed:@"ydwz_fh"] forState:(UIControlStateNormal)];
        [_topBackButton addTarget:self action:@selector(clickBackButton) forControlEvents:(UIControlEventTouchUpInside)];
    }
    return _topBackButton;
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


-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
    self.tabBarController.tabBar.hidden = YES;
}

-(void)viewWillDisappear:(BOOL)animated{
    [super viewWillDisappear:animated];
    self.tabBarController.tabBar.hidden = NO;

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
