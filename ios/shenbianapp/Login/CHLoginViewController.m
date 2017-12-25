//
//  CHLoginViewController.m
//  shenbianapp
//
//  Created by book on 2017/9/9.
//  Copyright © 2017 . All rights reserved.
//

#import "CHLoginViewController.h"

#import "CHLoginVCModel.h"

@interface CHLoginViewController ()<UITextViewDelegate>

@property(nonatomic,strong)UIView *contentView;
@property(nonatomic,strong) LXButton * backButton;
@property(nonatomic,strong) UIImageView *headImageView;
@property(nonatomic,strong) UIView *loginView;
@property(nonatomic,strong) UITextField *phoneNoTF;
@property(nonatomic,strong) UITextField *validCodeTF;
@property(nonatomic,strong) LXButton *obtainCodeBtn;

@property(nonatomic,strong) UIButton *loginButton;
@property(nonatomic,strong)UITextView *userAgreeTV;

@property(nonatomic,strong) UIView *thirdLoginView;

@property(nonatomic,strong) CHLoginVCModel *loginModel;
@property(nonatomic,strong) dispatch_source_t countTimer;
@end

@implementation CHLoginViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [self bindViewModel];
    self.view.backgroundColor = [UIColor whiteColor];
    
    [[UIApplication sharedApplication] setStatusBarStyle:(UIStatusBarStyleLightContent)];
    
    
    self.automaticallyAdjustsScrollViewInsets = NO;
    [self.view addSubview:self.contentView];
    [self.contentView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.left.right.equalTo(self.view);
        make.height.mas_equalTo(320);
    }];
    
    [self.contentView addSubview:self.backButton];
    [self.backButton mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(self.view).offset(15);
        make.top.equalTo(self.view).offset(30);
        make.width.mas_equalTo(10);
        make.height.mas_equalTo(18);
    }];
    
    [self.contentView addSubview:self.headImageView];
    [self.headImageView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.centerX.equalTo(self.contentView);
        make.top.equalTo(self.contentView).offset(90);
        make.width.height.mas_equalTo(90);
    }];
    
    [self.contentView addSubview:self.loginView];
    [self.loginView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(self.contentView).offset(15);
        make.right.equalTo(self.contentView).offset(-15);
        make.top.equalTo(self.headImageView.mas_bottom).offset(43);
        make.height.mas_equalTo(130);
    }];
    
    [self.loginView addSubview:self.phoneNoTF];
    [self.phoneNoTF mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(self.loginView).offset(15);
        make.right.equalTo(self.loginView).offset(-15);
        make.height.mas_equalTo(50);
        make.top.equalTo(self.loginView).offset(20);
    }];
    
    [self.loginView addSubview:self.validCodeTF];
    [self.validCodeTF mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(self.loginView).offset(15);
        make.right.equalTo(self.loginView).offset(-15);
        make.height.mas_equalTo(50);
        make.bottom.equalTo(self.loginView).offset(-10);
    }];
    
    [self.loginView addSubview:self.obtainCodeBtn];
    [self.obtainCodeBtn mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.loginView).offset(25);
        make.right.equalTo(self.loginView).offset(-15);
        make.width.mas_equalTo(80);
        make.height.mas_equalTo(40);
    }];
    
    
    [self.view addSubview:self.loginButton];
    [self.loginButton mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(self.view).offset(15);
        make.right.equalTo(self.view).offset(-15);
        make.top.equalTo(self.contentView.mas_bottom).offset(65);
        make.height.mas_equalTo(55);
    }];
    
    [self.view addSubview:self.userAgreeTV];
    [self.userAgreeTV mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.loginButton.mas_bottom).offset(12);
        make.left.right.equalTo(self.loginButton);
        make.height.mas_equalTo(50);
    }];
    
    [self.view addSubview:self.thirdLoginView];
    [self.thirdLoginView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.userAgreeTV.mas_bottom);
        make.left.right.equalTo(self.view);
        make.height.mas_equalTo(160);
    }];
    
}

- (void)bindViewModel{
    self.loginModel = [[CHLoginVCModel alloc]init];

    
}

-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
    self.navigationController.navigationBarHidden = YES;
    [[UINavigationBar appearance] setTintColor:[UIColor whiteColor]];
    [[UINavigationBar appearance] setBarTintColor:[UIColor colorWithHexColor:@"#009698"]];
    [[UIBarButtonItem appearance] setBackButtonTitlePositionAdjustment:UIOffsetMake(0, -60) forBarMetrics:UIBarMetricsDefault];
    self.navigationItem.backBarButtonItem.title = @"";
    
}



-(LXButton *)backButton{
    if (_backButton == nil) {
        _backButton = [LXButton buttonWithType:(UIButtonTypeCustom)];
        [_backButton  setImage:[UIImage imageNamed:@"tx_fh"] forState:(UIControlStateNormal)];
        [_backButton addTarget:self action:@selector(clickBackButton) forControlEvents:(UIControlEventTouchUpInside)];
    }
    return _backButton;
}

- (void)clickBackButton{
    
    [self dismissViewControllerAnimated:YES completion:nil];
}

-(UIView *)contentView{
    if (_contentView == nil) {
        _contentView = [UIView new];
        _contentView.backgroundColor = [UIColor colorWithHexString:@"#009698"];
    }
    return _contentView;
}

-(UIImageView *)headImageView{
    
    if (_headImageView == nil) {
        _headImageView = [[UIImageView alloc]init];
        _headImageView.layer.cornerRadius = 5;
        _headImageView.backgroundColor = [UIColor colorWithHexColor:@"#ebfeff"];
    }
    return _headImageView;
}

-(UIView *)loginView{
    if (_loginView == nil) {
        _loginView = [UIView new];
        _loginView.backgroundColor = [UIColor whiteColor];
        _loginView.layer.cornerRadius = 5;
        _loginView.layer.shadowColor = [UIColor blackColor].CGColor;
        _loginView.layer.shadowOpacity = 0.5;
        _loginView.layer.shadowRadius = 5;
        
        UIView *line = [UIView new];
        line.backgroundColor = [UIColor colorWithHexColor:@"#d9d9d9"];
        
        [_loginView addSubview:line];
        [line mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(_loginView).offset(15);
            make.right.equalTo(_loginView).offset(-15);
            make.height.mas_equalTo(1);
            make.center.equalTo(_loginView);
        }];
        
    }
    return _loginView;
}


-(UITextField *)phoneNoTF{
    if (_phoneNoTF == nil) {
        _phoneNoTF = [UITextField new];
        _phoneNoTF.placeholder = @"请输入手机号";
        _phoneNoTF.font = [UIFont systemFontOfSize:15];
        _phoneNoTF.keyboardType = UIKeyboardTypeNumberPad;
    }
    return _phoneNoTF;
}

-(UITextField *)validCodeTF{
    
    if (_validCodeTF == nil) {
        _validCodeTF = [[UITextField alloc]init];
        _validCodeTF.font = [UIFont systemFontOfSize:15];
        _validCodeTF.placeholder = @"验证码";
        _validCodeTF.keyboardType = UIKeyboardTypeNumberPad;
        
        
    }
    return  _validCodeTF;
}

-(LXButton *)obtainCodeBtn{
    if (_obtainCodeBtn == nil) {
        _obtainCodeBtn = [LXButton buttonWithType:(UIButtonTypeCustom)];
        _obtainCodeBtn.titleLabel.font = [UIFont systemFontOfSize:15];
        [_obtainCodeBtn setTitle:@"获取验证码" forState:(UIControlStateNormal)];
        [_obtainCodeBtn setTitleColor:[UIColor colorWithHexColor:@"#009698"] forState:(UIControlStateNormal)];
        [_obtainCodeBtn addTarget:self action:@selector(clickObtainValidCode) forControlEvents:(UIControlEventTouchUpInside)];
    }
    
    return _obtainCodeBtn;
}

- (void)clickObtainValidCode{
    
    NSString *text = self.phoneNoTF.text;
    if (text.length == 11 && [[text substringToIndex:1] isEqualToString:@"1"]) {
        NSDictionary *param = @{@"mobile":self.phoneNoTF.text};
        
        RACSignal *singal =  [self.loginModel.sendValidCode execute:param];
        
        [singal subscribeNext:^(NSDictionary *x) {

            self.loginModel.msgSessionId = [[x objectForKey:@"data"] objectForKey:@"smsSessionId"];
        } ];
        
        [self countDown];

    } else {
        
        UIAlertView *alert = [[UIAlertView alloc]init];
        alert.message =  @"手机号不正确";
        [alert addButtonWithTitle:@"确定"];
        [alert show];

    }
}
//倒计时

- (void)countDown{
    @weakify(self);
    __block NSInteger time = 59; //倒计时时间
    dispatch_queue_t queue = dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0);
    
    _countTimer = dispatch_source_create(DISPATCH_SOURCE_TYPE_TIMER, 0, 0, queue);
    
    dispatch_source_set_timer(_countTimer,DISPATCH_TIME_NOW,1.0*NSEC_PER_SEC, 0); //每秒
    
    dispatch_source_set_event_handler(_countTimer, ^{
        @strongify(self);
        if(time <= 0){ //倒计时结束，关闭
            
            dispatch_source_cancel(_countTimer);
            
            dispatch_async(dispatch_get_main_queue(), ^{
                [self.obtainCodeBtn setTitle:@"获取验证码" forState:(UIControlStateNormal)];
                self.obtainCodeBtn.userInteractionEnabled = YES;
            });
            
        } else {
            
            int seconds = time % 60;
            dispatch_async(dispatch_get_main_queue(), ^{
                //设置label读秒效果
               NSString *text = [NSString stringWithFormat:@"%d秒",seconds];
                [self.obtainCodeBtn setTitle:text forState:(UIControlStateNormal)];

                self.obtainCodeBtn.userInteractionEnabled = NO;
                
            });
            
            time--;
        }
    });
    
    dispatch_resume(_countTimer);
}

-(UIButton *)loginButton{
    if (_loginButton == nil) {
        _loginButton = [UIButton buttonWithType:(UIButtonTypeCustom)];
        _loginButton.backgroundColor = [UIColor colorWithHexColor:@"#009698"];
        _loginButton.layer.cornerRadius = 5;
        [_loginButton setTitle:@"登录" forState:(UIControlStateNormal)];
        [_loginButton addTarget:self action:@selector(clickLoginButton) forControlEvents:UIControlEventTouchUpInside];
    }
    
    return _loginButton;
}

-(void)clickLoginButton{
    
   
    NSString *text = self.phoneNoTF.text;
    if (text.length == 11 && [text hasPrefix:@"1"] && self.loginModel.msgSessionId.length > 0) {
    
        NSDictionary *param = @{@"mobile":self.phoneNoTF.text,@"code":self.validCodeTF.text,@"smsSessionId":self.loginModel.msgSessionId};
        RACSignal *signal =  [self.loginModel.loginCommand execute:param];
        [signal subscribeNext:^(id x) {
            NSInteger status = [[x objectForKey:@"status"] integerValue];
            if (status == 0) {
                [self dismissViewControllerAnimated:YES completion:nil];
            }
        } error:^(NSError *error) {
            NSLog(@"error:%@",error);
        }];
    
    } else {
        

    }
}

-(UITextView *)userAgreeTV{
    
    if (_userAgreeTV == nil) {
        _userAgreeTV = [UITextView new];
        NSMutableAttributedString *attrText =  [[NSMutableAttributedString alloc]initWithString:@"温馨提示：为注册身边的手机号，登录时将自动注册，且代表您已同意《用户服务协议》" attributes:@{NSFontAttributeName:[UIFont systemFontOfSize:12],NSForegroundColorAttributeName: [UIColor colorWithHexColor:@"#a2a5aa"]} ];
        [attrText addAttribute:NSLinkAttributeName value:@"" range:NSMakeRange(attrText.length - 8, 8) ];
        _userAgreeTV.attributedText = attrText;
        _userAgreeTV.linkTextAttributes = @{NSForegroundColorAttributeName:[UIColor colorWithHexColor:@"#009698"]};
        _userAgreeTV.delegate = self;
        _userAgreeTV.editable = NO;
        _userAgreeTV.scrollEnabled = NO;
        
    }
    
    return _userAgreeTV;
}


-(BOOL)textView:(UITextView *)textView shouldInteractWithURL:(NSURL *)URL inRange:(NSRange)characterRange{
    self.navigationController.navigationBarHidden = NO;
    UIViewController *agreeVC = [[UIViewController alloc]initWithNibName:@"CHLoginAgreement" bundle:nil];
    agreeVC.title = @"用户服务协议";
    [self.navigationController pushViewController:agreeVC animated:YES];
    
    return YES;
}


#pragma mark - 第三方登录

-(UIView *)thirdLoginView{
    
    if (_thirdLoginView == nil) {
        _thirdLoginView = [UIView new];
        
        UILabel *titleLabel = [UILabel new];
        titleLabel.text = @"第三方登录";
        titleLabel.font = [UIFont systemFontOfSize:13];
        titleLabel.textColor = [UIColor colorWithHexColor:@"#666666"];
        titleLabel.textAlignment = NSTextAlignmentCenter;
        [_thirdLoginView addSubview:titleLabel];
        [titleLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.centerX.equalTo(_thirdLoginView);
            make.top.equalTo(_thirdLoginView).offset(20);
            make.left.right.equalTo(_thirdLoginView);
            make.height.mas_equalTo(20);
        }];
        
        UIButton *wxLoginBtn = [UIButton buttonWithType:(UIButtonTypeCustom)];
        [wxLoginBtn setImage:[UIImage imageNamed:@"dl_wx"] forState:(UIControlStateNormal)];
        [_thirdLoginView addSubview:wxLoginBtn];
        [wxLoginBtn mas_makeConstraints:^(MASConstraintMaker *make) {
            make.centerX.equalTo(_thirdLoginView).offset(-70);
            make.bottom.equalTo(_thirdLoginView).offset(-55);
            make.width.mas_equalTo(32);
            make.height.mas_equalTo(27);
        }];
        
        UIButton *zfbLoginBtn = [UIButton buttonWithType:(UIButtonTypeCustom)];
        [zfbLoginBtn setImage:[UIImage imageNamed:@"dl_zfb"] forState:(UIControlStateNormal)];
        [_thirdLoginView addSubview:zfbLoginBtn];
        [zfbLoginBtn mas_makeConstraints:^(MASConstraintMaker *make) {
            make.centerX.equalTo(_thirdLoginView).offset(70);
            make.bottom.equalTo(_thirdLoginView).offset(-55);
            make.width.mas_equalTo(32);
            make.height.mas_equalTo(28);
        }];
        
    }
    return _thirdLoginView;
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
