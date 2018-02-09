//
//  CHPublishViewController.m
//  shenbianapp
//
//  Created by book on 2017/8/19.
//  Copyright © 2017 helinkeji. All rights reserved.
//

#import "CHPublishViewController.h"

#import "CHPublishServeViewController.h"
#import "CHPublishArticleViewController.h"

@interface CHPublishViewController ()
@property(nonatomic,strong) UIView *contentView;

@property(nonatomic,strong) LXButton *articleBtn;
@property(nonatomic,strong) LXButton *serviceBtn;
@property(nonatomic,strong) LXButton *closeBtn;
@end

@implementation CHPublishViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.navigationController.navigationBarHidden = YES;
    self.modalPresentationStyle = UIModalPresentationFullScreen;
    self.navigationController.navigationBar.tintColor = [UIColor blackColor];
    self.topView.hidden = YES;
    self.view.backgroundColor = [UIColor clearColor];
    [self.view addSubview:self.contentView];
    [self.contentView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.bottom.equalTo(self.view);
        make.left.equalTo(self.view).offset(15);
        make.right.equalTo(self.view).offset(-15);
        make.height.mas_equalTo(200);
    }];
    [self.contentView addSubview:self.articleBtn];
    [self.contentView addSubview:self.serviceBtn];
    
    [self.articleBtn mas_makeConstraints:^(MASConstraintMaker *make) {
        make.centerY.equalTo(self.contentView).offset(-30);
        make.right.equalTo(self.contentView.mas_centerX).offset(-30);
        make.width.height.mas_equalTo(80);
    }];
    [self.serviceBtn mas_makeConstraints:^(MASConstraintMaker *make) {
        make.centerY.equalTo(self.contentView).offset(-30);
        make.left.equalTo(self.contentView.mas_centerX).offset(30);
        make.width.height.mas_equalTo(80);
    }];
    [self.contentView addSubview:self.closeBtn];
    [self.closeBtn mas_makeConstraints:^(MASConstraintMaker *make) {
        make.centerX.equalTo(self.view);
        make.bottom.equalTo(self.view).offset(-18);
        make.width.height.mas_equalTo(30);
    }];
    
}



-(UIView *)contentView{
    if (_contentView == nil) {
        _contentView = [UIView new];
        _contentView.backgroundColor = [UIColor whiteColor];
        _contentView.layer.cornerRadius = 5;
        _contentView.layer.shadowColor = [UIColor colorWithDisplayP3Red:185.0/255 green:185.0/255 blue:185.5/255 alpha:1].CGColor;
        _contentView.layer.shadowOpacity = 0.5;
        _contentView.layer.shadowRadius = 10;
        
    }
    return _contentView;
}

-(LXButton *)articleBtn{
    if (_articleBtn == nil) {
        _articleBtn = [LXButton buttonWithType:(UIButtonTypeCustom)];
        [_articleBtn setTitle:@"文章" forState:(UIControlStateNormal)];
        _articleBtn.titleEdgeInsets = UIEdgeInsetsMake(0, -80, -100, 0);
        [_articleBtn setTitleColor:[UIColor colorWithHexColor:@"#4a7ae2"] forState:(UIControlStateNormal)];
        _articleBtn.titleLabel.font = [UIFont systemFontOfSize:13];
        [_articleBtn setImage:[UIImage imageNamed:@"tk_wz"] forState:(UIControlStateNormal)];

        [_articleBtn addTarget:self action:@selector(publishArticle) forControlEvents:(UIControlEventTouchUpInside)];
    }
    return _articleBtn;
}

-(LXButton *)serviceBtn{
    if (_serviceBtn == nil) {
        _serviceBtn = [LXButton buttonWithType:(UIButtonTypeCustom)];
        [_serviceBtn setTitle:@"服务" forState:(UIControlStateNormal)];
        _serviceBtn.titleEdgeInsets = UIEdgeInsetsMake(0, -80, -100, 0);
        [_serviceBtn setTitleColor:[UIColor colorWithHexColor:@"##fd8469"] forState:(UIControlStateNormal)];
        _serviceBtn.titleLabel.font = [UIFont systemFontOfSize:13];
        [_serviceBtn setImage:[UIImage imageNamed:@"tk_fw"] forState:(UIControlStateNormal)];
        [_serviceBtn addTarget:self action:@selector(publishService) forControlEvents:(UIControlEventTouchUpInside)];
    }
    return _serviceBtn;
}

- (void)publishArticle{
    
//    UIViewController *presentVC = self.presentingViewController;
//    [self dismissViewControllerAnimated:NO completion:^{
//
//        CHPublishArticleViewController *articleVC = [[CHPublishArticleViewController alloc]init];
//        [presentVC presentViewController:articleVC animated:YES completion:nil];
//    }];
    
    UIAlertView *alertView = [[UIAlertView alloc]initWithTitle:@"温馨提示" message:@"该功能暂不提供，程序员小哥正在努力开发中，将在后续版本中为您亲情呈现！" delegate:nil cancelButtonTitle:@"知晓" otherButtonTitles:nil];
    [alertView show];
}

- (void)publishService{

    UIViewController *presentVC = self.presentingViewController;
    [self dismissViewControllerAnimated:NO completion:^{
        NSString* token = [[NSUserDefaults standardUserDefaults] objectForKey:@"token"];
        if (token == nil) {
            [[NSNotificationCenter defaultCenter] postNotificationName:kCHNotificationLogin object:nil];
        } else {
        CHPublishServeViewController *serveVC = [[CHPublishServeViewController alloc]init];
        UINavigationController *nav = [[UINavigationController alloc]initWithRootViewController:serveVC];
        [presentVC presentViewController:nav animated:YES completion:nil];
        }
    }];
    
}

-(LXButton *)closeBtn{
    if (_closeBtn == nil) {
        _closeBtn = [LXButton buttonWithType:(UIButtonTypeCustom)];
        [_closeBtn addTarget:self action:@selector(disMiss) forControlEvents:(UIControlEventTouchUpInside)];
        [_closeBtn setImage:[UIImage imageNamed:@"fbwz_gb"] forState:(UIControlStateNormal)];
    }
    return _closeBtn;
}

- (void)disMiss{

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
