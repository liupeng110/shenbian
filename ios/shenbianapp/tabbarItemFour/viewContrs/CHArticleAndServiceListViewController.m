//
//  CHArticleAndServiceListViewController.m
//  shenbianapp
//
//  Created by book on 2017/9/17.
//  Copyright © 2017年 陈坚. All rights reserved.
//

#import "CHArticleAndServiceListViewController.h"

@interface CHArticleAndServiceListViewController ()<UITableViewDelegate,UITableViewDataSource>

@property(nonatomic,strong) UIView *topview;
@property(nonatomic,strong) UIButton *myArticleButton;
@property(nonatomic,strong) UIButton *myServiceButton;
@property(nonatomic,strong) UITableView *tableView;

@end

@implementation CHArticleAndServiceListViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.

    self.rightButton.hidden = YES;
    
    [self.view addSubview:self.topview];
    [self.topview mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.equalTo(self.view);
        make.top.equalTo(self.view).offset(64);
        make.height.mas_equalTo(49);
    }];
    
    [self.topview addSubview:self.myArticleButton];
    [self.myArticleButton mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.left.bottom.equalTo(self.topview);
        make.width.mas_equalTo(kScreenWidth/2);
    }];
    
    [self.topview addSubview:self.myServiceButton];
    [self.myServiceButton mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.right.bottom.equalTo(self.topview);
        make.width.mas_equalTo(kScreenWidth/2);
    }];
    
    [self.view addSubview:self.tableView];
    [self.tableView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.bottom.equalTo(self.view);
        make.top.equalTo(self.topview.mas_bottom).offset(5);
    }];
    
}

-(UIView *)topview{
    if (_topview == nil) {
        _topview = [UIView new];
        _topview.backgroundColor = [UIColor colorWithHexString:@"#fefefe"];
        _topview.layer.shadowColor = [UIColor blackColor].CGColor;
        _topview.layer.shadowOpacity = 0.8;
    }
    return _topview;
}

-(UIButton *)myArticleButton{
    if (_myArticleButton == nil) {
        _myArticleButton = [UIButton buttonWithType:(UIButtonTypeCustom)];
        [_myArticleButton setTitle:@"文章" forState:(UIControlStateNormal)];
        [_myArticleButton setTitleColor:[UIColor colorWithHexString:@"#009698"] forState:(UIControlStateNormal)];
        _myArticleButton.titleLabel.font = [UIFont systemFontOfSize:15];
    }
    return _myArticleButton;
}

-(UIButton *)myServiceButton{

    if (_myServiceButton == nil) {
        _myServiceButton = [UIButton buttonWithType:(UIButtonTypeCustom)];
        _myServiceButton.titleLabel.font = [UIFont systemFontOfSize:15];
        [_myServiceButton setTitle:@"服务" forState:(UIControlStateNormal)];
        [_myServiceButton setTitleColor:[UIColor colorWithHexString:@"#4f5965"] forState:(UIControlStateNormal)];
    }
    
    return _myServiceButton;
}

-(UITableView *)tableView{

    if (_tableView == nil) {
        _tableView = [[UITableView alloc]init];
        _tableView.delegate = self;
        _tableView.dataSource = self;
        [_tableView registerClass:[UITableViewCell class] forCellReuseIdentifier:@"myCell"];
    }
    return _tableView;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{

    return 3;
}

-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"myCell"];
    
    return cell;
}



@end
