//
//  CHOverBalanceViewController.m
//  shenbianapp
//
//  Created by book on 2017/11/13.
//  Copyright © 2017年 helinkeji. All rights reserved.
//

#import "CHOverBalanceViewController.h"

@interface CHOverBalanceViewController ()

@property(nonatomic,strong)UIWebView *webView;

@end

@implementation CHOverBalanceViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.webView = [[UIWebView alloc]initWithFrame:(CGRectMake(0, 64, kScreenWidth, kScreenHeight - 113))];
    [self.view addSubview:self.webView];
    self.webView.backgroundColor = [UIColor orangeColor];
    
    NSURLRequest *request = [NSURLRequest requestWithURL:[NSURL URLWithString:@"http://www.baidu.com"]];
    [self.webView loadRequest:request];
   
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    //Dispose of any resources that can be recreated.
    
}

@end
