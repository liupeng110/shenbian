//
//  CHServiceDetailsViewController.m
//  shenbianapp
//
//  Created by book on 2017/9/12.
//  Copyright © 2017年 . All rights reserved.
//

#import "CHServiceDetailsViewController.h"

#import "CHSubmitOrderViewController.h"
#import "CHChatRoomViewController.h"
@interface CHServiceDetailsViewController ()
@property(nonatomic,strong) CHServiceUperView * topView;
@property(nonatomic,strong)CHServiceBottomView *bottomView;

@property(nonatomic,strong)UIButton *favoriteButton;
@property(nonatomic,strong)UIButton *shareButton;

@property(nonatomic,strong)NSArray *dataArray;

@end

@implementation CHServiceDetailsViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.

}

-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
    self.tabBarController.tabBar.hidden = YES;
    self.navigationController.navigationBarHidden = YES;

}

-(void)viewWillDisappear:(BOOL)animated{
    [super viewWillDisappear:animated];
    self.tabBarController.tabBar.hidden = NO;
    self.navigationController.navigationBarHidden = NO;
    
}
-(void)bindViewControllerModel{
    @weakify(self);
    self.bottomView.sendMessage = ^{
        @strongify(self);
        CHChatRoomViewController *chatRoom = [[CHChatRoomViewController alloc]initWithConversationType:ConversationType_PRIVATE targetId:@"1"];
        [self.navigationController pushViewController:chatRoom animated:YES];
    };
    self.bottomView.makeOrder = ^{
        @strongify(self);
        CHSubmitOrderViewController *submitOrder = [CHSubmitOrderViewController new];
        [self.navigationController pushViewController:submitOrder animated:YES];
    };
}

-(void)setupViews{
    
    [self.view addSubview:self.topView];
    [self.topView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.top.equalTo(self.view);
        make.bottom.mas_equalTo(-55);
    }];
    
    [self.view addSubview:self.bottomView];
    [self.bottomView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.equalTo(self.view);
        make.bottom.equalTo(self.view);
        make.height.mas_equalTo(55);
    }];
    
    [self.view addSubview:self.shareButton];
    [self.shareButton mas_makeConstraints:^(MASConstraintMaker *make) {
        make.right.equalTo(self.view).offset(-15);
        make.top.equalTo(self.view).offset(20);
        make.width.height.mas_equalTo(40);
    }];
    
    [self.view addSubview:self.favoriteButton];
    [self.favoriteButton mas_makeConstraints:^(MASConstraintMaker *make) {
        make.right.equalTo(self.shareButton.mas_left).offset(-15);
        make.top.equalTo(self.shareButton);
        make.width.height.mas_equalTo(40);
    }];
    
    UIButton *backButton = [UIButton buttonWithType:(UIButtonTypeCustom)];
    [backButton setImage:[UIImage imageNamed:@"ydwz_fh"] forState:(UIControlStateNormal)];
    [backButton addTarget:self action:@selector(clickBackButton) forControlEvents:(UIControlEventTouchUpInside)];
    [self.view addSubview:backButton];
    [backButton mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(self.view).offset(7);
        make.top.equalTo(self.shareButton);
        make.width.height.mas_equalTo(40);
    }];
}

-(UIView *)topView{
    if (_topView == nil) {
        _topView = [CHServiceUperView new];
        _topView.backgroundColor = [UIColor colorWithHexString:@"#f9ee82"];
    }
    return _topView;
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


-(UIButton *)shareButton{
    if (_shareButton == nil) {
        _shareButton = [UIButton buttonWithType:(UIButtonTypeCustom)];
        [_shareButton setImage:[UIImage imageNamed:@"ydwz_zf"] forState:(UIControlStateNormal)];
        [_shareButton addTarget:self action:@selector(clickShareButton) forControlEvents:(UIControlEventTouchUpInside)];
        _shareButton.frame = CGRectMake(0, 0, 40, 40);
    }
    
    return _shareButton;
}

-(void)clickShareButton{
    
}


-(UIButton *)favoriteButton{
    if (_favoriteButton == nil) {
        _favoriteButton = [UIButton buttonWithType:(UIButtonTypeCustom)];
        _favoriteButton.frame = CGRectMake(0, 0, 40, 40);
        [_favoriteButton setImage:[UIImage imageNamed:@"ydwz_sc"] forState:UIControlStateNormal];
        [_favoriteButton addTarget:self action:@selector(clickfavoriteButton) forControlEvents:(UIControlEventTouchUpInside)];
        
    }
    return _favoriteButton;
}

-(void)clickfavoriteButton {
    
}

-(void)dealloc{
    
    NSLog(@"xxx dealloc");
    
}
@end
