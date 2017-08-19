//
//  CHPublishViewController.m
//  shenbianapp
//
//  Created by book on 2017/8/19.
//  Copyright © 2017年 helinkeji. All rights reserved.
//

#import "CHPublishViewController.h"

#import "CHPublishServeViewController.h"
#import "CHPublishArticleViewController.h"

@interface CHPublishViewController ()
@property(nonatomic,strong) UIView *contentView;
@property(nonatomic,strong) LXButton *closeBtn;
@property(nonatomic,strong) LXButton *articleBtn;
@property(nonatomic,strong) LXButton *serveBtn;

@end

@implementation CHPublishViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.navigationController.navigationBarHidden = YES;
    self.view.backgroundColor = [[UIColor blackColor] colorWithAlphaComponent:0.3];
    [self.view addSubview:self.closeBtn];
    [self.view addSubview:self.contentView];
    [self.contentView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.bottom.right.equalTo(self.view);
        make.height.mas_equalTo(300);
    }];
    [self.closeBtn mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.top.equalTo(self.view).offset(20);
        make.width.height.mas_equalTo(40);
    }];
    
    [self.contentView addSubview:self.articleBtn];
    [self.contentView addSubview:self.serveBtn];
    
    [self.articleBtn mas_makeConstraints:^(MASConstraintMaker *make) {
        make.centerY.equalTo(self.contentView);
        make.right.equalTo(self.contentView.mas_centerX).offset(-10);
        make.width.height.mas_equalTo(80);
    }];
    [self.serveBtn mas_makeConstraints:^(MASConstraintMaker *make) {
        make.centerY.equalTo(self.contentView);
        make.left.equalTo(self.contentView.mas_centerX).offset(10);
        make.width.height.mas_equalTo(80);
    }];
}

-(LXButton *)closeBtn{
    if (_closeBtn == nil) {
        _closeBtn = [LXButton buttonWithType:(UIButtonTypeCustom)];
        [_closeBtn setImage:[UIImage imageNamed:@"close_page"] forState:(UIControlStateNormal)];
        [_closeBtn addTarget:self action:@selector(dissMiss) forControlEvents:(UIControlEventTouchDown)];
    }
    return _closeBtn;
}

-(UIView *)contentView{
    if (_contentView == nil) {
        _contentView = [UIView new];
        _contentView.backgroundColor = [UIColor whiteColor];
        
    }
    return _contentView;
}

-(LXButton *)articleBtn{
    if (_articleBtn == nil) {
        _articleBtn = [LXButton buttonWithType:(UIButtonTypeCustom)];
        [_articleBtn setTitle:@"发布文章" forState:(UIControlStateNormal)];
        [_articleBtn setTitleColor:[UIColor blackColor] forState:(UIControlStateNormal)];
        _articleBtn.backgroundColor = [UIColor purpleColor];
        [_articleBtn addTarget:self action:@selector(publishArticle) forControlEvents:(UIControlEventTouchUpInside)];
    }
    return _articleBtn;
}

-(LXButton *)serveBtn{
    if (_serveBtn == nil) {
        _serveBtn = [LXButton buttonWithType:(UIButtonTypeCustom)];
        [_serveBtn setTitle:@"发布服务" forState:(UIControlStateNormal)];
        [_serveBtn setTitleColor:[UIColor blackColor] forState:(UIControlStateNormal)];
        _serveBtn.backgroundColor = [UIColor purpleColor];
        [_serveBtn addTarget:self action:@selector(publishServe) forControlEvents:(UIControlEventTouchUpInside)];
    }
    return _serveBtn;
}

- (void)publishArticle{
    
    [self dismissViewControllerAnimated:NO completion:nil];

    UIViewController *presentedVC =   self.presentingViewController;
    NSLog(@"presentedVC:%@",presentedVC);
    CHPublishArticleViewController *articleVC = [[CHPublishArticleViewController alloc]init];
    
    [presentedVC presentViewController:articleVC animated:YES completion:nil];
    

}

- (void)publishServe{
    
    
}

- (void)dissMiss{
    
    [self dismissViewControllerAnimated:NO completion:nil];
    
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
