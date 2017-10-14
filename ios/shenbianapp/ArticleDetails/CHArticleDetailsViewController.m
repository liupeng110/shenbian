//
//  CHArticleDetailsViewController.m
//  shenbianapp
//
//  Created by book on 2017/9/17.
//  Copyright © 2017年 陈坚. All rights reserved.
//


#import "CHArticleDetailsViewController.h"

#import "CHArticleDetailsTableViewCell.h"


@interface CHArticleDetailsViewController ()<UITableViewDelegate,UITableViewDataSource>

@property(nonatomic,strong)UIButton *collectButton;
@property(nonatomic,strong)UIButton *topShareButton;

@property(nonatomic,strong) UITableView *tableView;

@end

@implementation CHArticleDetailsViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.

    [self.backButton setImage:[UIImage imageNamed:@"ydwz_fh"] forState:(UIControlStateNormal)];
    self.navigationController.navigationBar.barTintColor = [UIColor whiteColor];

}

- (void)setupViews{
    self.navigationItem.rightBarButtonItem = nil;

    UIBarButtonItem *shareItem = [[UIBarButtonItem alloc]initWithCustomView:self.topShareButton];
    UIBarButtonItem *collectItem = [[UIBarButtonItem alloc]initWithCustomView:self.collectButton ];
    self.navigationItem.rightBarButtonItems = @[shareItem,collectItem];
    
    [self.view addSubview:self.tableView];
    [self.tableView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.equalTo(self.view);
        make.top.equalTo(self.view).offset(64);
        make.bottom.equalTo(self.view).offset(-49);
    }];
    
}

-(void)bindViewControllerModel{


}

-(UIButton *)topShareButton{
    if (_topShareButton == nil) {
        _topShareButton = [UIButton buttonWithType:(UIButtonTypeCustom)];
        [_topShareButton setImage:[UIImage imageNamed:@"ydwz_zf"] forState:(UIControlStateNormal)];
        [_topShareButton addTarget:self action:@selector(clickShareButton) forControlEvents:(UIControlEventTouchUpInside)];
        _topShareButton.frame = CGRectMake(0, 0, 40, 40);
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
        [_collectButton addTarget:self action:@selector(clickCollectButton) forControlEvents:(UIControlEventTouchUpInside)];

    }
    return _collectButton;
}

-(void)clickCollectButton{
    
}

#pragma -mark tableview delegate

-(UITableView *)tableView{

    if (!_tableView) {
        _tableView = [UITableView new];
        [_tableView registerClass:[CHArticleDetailsTableViewCell class] forCellReuseIdentifier:@"articleCell"];
        _tableView.delegate = self;
        _tableView.dataSource = self;
    }
    return _tableView;

}


-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{

    return 3;
}

-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{

    CHArticleDetailsTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"articleCell" forIndexPath:indexPath];
    cell.selectionStyle = UITableViewCellSelectionStyleNone;
    return cell;
}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{

    return 500;
}


-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{


}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


@end
