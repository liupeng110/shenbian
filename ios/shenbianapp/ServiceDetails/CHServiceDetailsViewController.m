//
//  CHServiceDetailsViewController.m
//  shenbianapp
//
//  Created by book on 2017/9/12.
//  Copyright © 2017 . All rights reserved.
//

#import "CHServiceDetailsViewController.h"

#import "CHSubmitOrderViewController.h"
#import "CHChatRoomViewController.h"
#import "CHServiceDetailModel.h"
#import "CHPersonHomeViewController.h"
@interface CHServiceDetailsViewController ()
@property(nonatomic,strong) CHServiceUperView * topView;
@property(nonatomic,strong)CHServiceBottomView *bottomView;

@property(nonatomic,strong)UIButton *favoriteButton;
@property(nonatomic,strong)UIButton *shareButton;

@property(nonatomic,strong)NSArray *dataArray;
@property(nonatomic,strong) CHServiceDetailModel *serviceModel;
@property(nonatomic,copy)NSDictionary *userDic;
@end

@implementation CHServiceDetailsViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    WXMediaMessage *message = [WXMediaMessage message];
    message.title = @"";
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

-(CHServiceDetailModel *)serviceModel{
    if (!_serviceModel) {
        _serviceModel = [CHServiceDetailModel new];
    }
    return _serviceModel;
}

-(void)bindViewControllerModel{
    
    if(self.serviceId == nil)  return;
    
    RACSignal *signal = [self.serviceModel.loadPagedata execute:@{@"serviceId":self.serviceId}];
    @weakify(self);
    [signal subscribeNext:^(id x) {
        NSLog(@"service details:%@",x);
        @strongify(self);
        
        NSDictionary *resultDic = [x objectForKey:@"data"];
        CHServiceDetailModel *model = [CHServiceDetailModel new];
        model.serviceTitle = [resultDic objectForKey:@"serviceTitle"];
        model.serviceContent = [resultDic objectForKey:@"serviceDescription"];
        model.serviceType =  [[resultDic objectForKey:@"serviceType"] integerValue];
        model.servicePrice = [resultDic objectForKey:@"servicePrice"];
        model.commentCount = [resultDic objectForKey:@"evaluateCount"];
        model.commentList = [resultDic objectForKey:@"evaluates"];
        model.locationStr = [resultDic objectForKey:@"location"];
        model.userName  = [resultDic objectForKey:@"userName"];
        model.userIconUrl = [resultDic objectForKey:@"userIcon"];
        model.recommendList = [resultDic objectForKey:@"recommends"];
        model.advertisementList = [resultDic objectForKey:@"advs"];
        model.starRating = [resultDic objectForKey:@"starRating"];
        model.userId = [resultDic objectForKey:@"userId"];
        self.topView.model = model;
        self.serviceModel = model;
        self.userDic = resultDic;
    } error:^(NSError *error) {
        NSLog(@"error:%@",error);
    }];
    
    self.bottomView.sendMessage = ^{
        @strongify(self);
        NSString *token = [[NSUserDefaults standardUserDefaults] objectForKey:@"token"];
        
        if (token) {
            NSString *userId = [NSString stringWithFormat:@"%@",self.serviceModel.userId];
            CHChatRoomViewController *chatRoom = [[CHChatRoomViewController alloc]initWithConversationType:ConversationType_PRIVATE targetId:userId];
            chatRoom.title = self.serviceModel.userName;
            [self.navigationController pushViewController:chatRoom animated:YES];
            
        } else {
            [[NSNotificationCenter defaultCenter] postNotificationName:kCHNotificationLogin object:nil];
        }
    };
    self.bottomView.makeOrder = ^{
        @strongify(self);
        NSString *token = [[NSUserDefaults standardUserDefaults] objectForKey:@"token"];
        
        if (token && self.serviceModel) {
            
            CHSubmitOrderViewController *submitOrder = [CHSubmitOrderViewController new];
            submitOrder.dataList = @[@{@"carts":@[@{@"serviceId":self.serviceId,@"serviceTitle":self.serviceModel.serviceTitle,@"price":self.serviceModel.servicePrice,@"serviceAmount":@"1",@"createTime":@"",@"homeUrl":@"",@"serviceDescription":self.serviceModel.serviceContent},],@"shopName":self.serviceModel.userName,@"userIcon":self.serviceModel.userIconUrl}];
            
            [self.navigationController pushViewController:submitOrder animated:YES];
            
        } else {
            
            [[NSNotificationCenter defaultCenter] postNotificationName:kCHNotificationLogin object:nil];
        }
    };
    
    self.topView.clickAddShopCart = ^{
        @strongify(self);
        
        NSString *token = [[NSUserDefaults standardUserDefaults] objectForKey:@"token"];
        if (token) {
            
            
            RACSignal *signal = [self.serviceModel.addToCartCommand execute:@{@"serviceId":self.serviceId,@"token":token,@"amount":@"1"}];
            __block NSString *message = @"";
            [signal subscribeNext:^(id x) {
                message = @"添加购物车成功";
                UIAlertView *alertView = [[UIAlertView alloc]initWithTitle:@"温馨提示" message:message delegate:nil cancelButtonTitle:nil otherButtonTitles:@"知晓", nil];
                [alertView show];
            } error:^(NSError *error) {
                message = @"添加购物车失败";
                UIAlertView *alertView = [[UIAlertView alloc]initWithTitle:@"温馨提示" message:message delegate:nil cancelButtonTitle:nil otherButtonTitles:@"知晓", nil];
                [alertView show];
            }];
        } else {
            [[NSNotificationCenter defaultCenter] postNotificationName:kCHNotificationLogin object:nil];
            
        }
        
    };
    
    self.topView.tapHeadImagegGuesture = ^{
        @strongify(self);
        NSString *token = [[NSUserDefaults standardUserDefaults] objectForKey:@"token"];
        
        if (self.userDic &&  token) {
            CHPersonHomeViewController *serviceDetail = [[CHPersonHomeViewController alloc]init];
            serviceDetail.userName = [self.userDic objectForKey:@"userName"];
            serviceDetail.userIcon = [self.userDic objectForKey:@"urserIcon"];
            serviceDetail.userId = [self.userDic objectForKey:@"userId"];
            [self.navigationController pushViewController:serviceDetail animated:YES];
        } else {
            [[NSNotificationCenter defaultCenter] postNotificationName:kCHNotificationLogin object:nil];
        }
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
    UIView *maskView = [UIView new];
    [self.view addSubview:maskView];
    maskView.userInteractionEnabled = YES;
    maskView.backgroundColor = [[UIColor blackColor] colorWithAlphaComponent:0.5];
    [maskView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.edges.equalTo(self.view);
    }];
    UIView *bottomView = [UIView new];
    [maskView addSubview:bottomView];
    bottomView.userInteractionEnabled = YES;
    bottomView.backgroundColor = [UIColor whiteColor];
    [bottomView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.bottom.equalTo(maskView);
        make.height.mas_equalTo(200);
    }];
    
    UIImageView *imageViewPy = [UIImageView new];
    imageViewPy.userInteractionEnabled = YES;
    imageViewPy.image = [UIImage imageNamed:@"fwxq_py"];
    [bottomView addSubview:imageViewPy];
    [imageViewPy mas_makeConstraints:^(MASConstraintMaker *make) {
        make.centerY.equalTo(bottomView);
        make.centerX.equalTo(bottomView).offset(-60);
        make.width.height.mas_equalTo(60);
    }];
    
    UIImageView *imageViewPyq = [[UIImageView alloc]initWithImage:[UIImage imageNamed:@"fwxq_pyq"]];
    imageViewPyq.userInteractionEnabled = YES;
    [bottomView addSubview:imageViewPyq];
    [imageViewPyq mas_makeConstraints:^(MASConstraintMaker *make) {
        make.centerY.equalTo(bottomView);
        make.centerX.equalTo(bottomView).offset(50);
        make.width.height.mas_equalTo(60);
        
    }];
    @weakify(self);
    UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc]initWithActionBlock:^(id  _Nonnull sender) {
        [maskView removeFromSuperview];
        
    }];
    [maskView addGestureRecognizer:tap];
    
    UITapGestureRecognizer *tapPy = [[UITapGestureRecognizer alloc]initWithActionBlock:^(id  _Nonnull sender) {
        @strongify(self);
        WXMediaMessage *message = [WXMediaMessage message];
        message.title = [self.userDic objectForKey:@"serviceTitle"];
        message.description = [self.userDic objectForKey:@"serviceDescription"];
        [message setThumbImage:[UIImage imageNamed:@"default_image"]];
        
        WXWebpageObject *webobject = [WXWebpageObject object];
        webobject.webpageUrl = @"https://itunesconnect.apple.com/WebObjects/iTunesConnect.woa/ra/ng/app/1288095892";
        message.mediaObject = webobject;
        
        SendMessageToWXReq *req = [SendMessageToWXReq new];
        req.bText = NO;
        req.message = message;
        req.scene = WXSceneSession;
        
        [WXApi sendReq:req];
        [maskView removeFromSuperview];
        
    }];
    [imageViewPy addGestureRecognizer:tapPy];
    
    
    UITapGestureRecognizer *tapPyq = [[UITapGestureRecognizer alloc]initWithActionBlock:^(id  _Nonnull sender) {
        @strongify(self);
        WXMediaMessage *message = [WXMediaMessage message];
        message.title = [self.userDic objectForKey:@"serviceTitle"];
        message.description = [self.userDic objectForKey:@"serviceDescription"];
        [message setThumbImage:[UIImage imageNamed:@"default_image"]];
        
        WXWebpageObject *webobject = [WXWebpageObject object];
        webobject.webpageUrl = @"https://itunesconnect.apple.com/WebObjects/iTunesConnect.woa/ra/ng/app/1288095892";
        message.mediaObject = webobject;
        
        SendMessageToWXReq *req = [SendMessageToWXReq new];
        req.bText = NO;
        req.message = message;
        req.scene = WXSceneTimeline;
        
        [WXApi sendReq:req];
        [maskView removeFromSuperview];
        
    }];
    [imageViewPyq addGestureRecognizer:tapPyq];
    
}

-(UIButton *)favoriteButton{
    if (_favoriteButton == nil) {
        _favoriteButton = [UIButton buttonWithType:(UIButtonTypeCustom)];
        _favoriteButton.frame = CGRectMake(0, 0, 40, 40);
        [_favoriteButton setImage:[UIImage imageNamed:@"ydwz_sch"] forState:UIControlStateNormal];
        [_favoriteButton addTarget:self action:@selector(clickfavoriteButton:) forControlEvents:(UIControlEventTouchUpInside)];
        
    }
    return _favoriteButton;
}

-(void)clickfavoriteButton:(UIButton*)button {
    NSString *message = @"";
    if (button.tag == 0) {
        [button setImage:[UIImage imageNamed:@"ydwz_sc"] forState:(UIControlStateNormal)];
        button.tag = 1;
        message = @"收藏成功";
    } else {
        [button setImage:[UIImage imageNamed:@"ydwz_sch"] forState:(UIControlStateNormal)];
        button.tag = 0;
        message = @"已取消收藏";
    }
    
    UIAlertView *alert = [[UIAlertView alloc]initWithTitle:@"温馨提示" message:message delegate:nil
                                         cancelButtonTitle:@"知晓" otherButtonTitles:nil];
    [alert show];
    NSString *concernStatus = [NSString stringWithFormat:@"%ld",button.tag];
    NSString *token  = [[NSUserDefaults standardUserDefaults] objectForKey:@"token"];
    
    if (token) {
        
        RACSignal *singal = [self.serviceModel.focusCommand execute:@{@"token":token,@"concernStatus":concernStatus}];
        [singal subscribeNext:^(id x) {
            
            
        } error:^(NSError *error) {
            NSLog(@"error:%@",error);
        }];
    } else {
        [[NSNotificationCenter defaultCenter] postNotificationName:kCHNotificationLogin object:nil];
    }
}

-(void)dealloc{
    
    NSLog(@"xxx dealloc");
    
}
@end
