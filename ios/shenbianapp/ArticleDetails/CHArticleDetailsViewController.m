//
//  CHArticleDetailsViewController.m
//  shenbianapp
//
//  Created by book on 2017/9/17.
//  Copyright © 2017. All rights reserved.
//


#import "CHArticleDetailsViewController.h"

#import "CHArticleDetailsTableViewCell.h"


@interface CHArticleDetailsViewController ()<UITableViewDelegate,UITableViewDataSource>

@property(nonatomic,strong)UIButton *favoriteButton;
@property(nonatomic,strong)UIButton *shareButton;

@property(nonatomic,strong) UITableView *tableView;

@end

@implementation CHArticleDetailsViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.

    [self.backButton setImage:[UIImage imageNamed:@"ydwz_fh"] forState:(UIControlStateNormal)];
    self.navigationController.navigationBar.barTintColor = [UIColor whiteColor];
   
    UIBarButtonItem *shareItem = [[UIBarButtonItem alloc]initWithCustomView:self.shareButton];
    UIBarButtonItem *favoriteItem = [[UIBarButtonItem alloc]initWithCustomView:self.favoriteButton ];
    UIBarButtonItem *spaceItem = [[UIBarButtonItem alloc]initWithBarButtonSystemItem:UIBarButtonSystemItemFixedSpace target:nil action:nil];
    
    self.navigationItem.rightBarButtonItems = @[shareItem,spaceItem,favoriteItem];
}

-(void)viewWillDisappear:(BOOL)animated{
    [super viewWillDisappear:animated];
    self.navigationController.navigationBar.barTintColor = [UIColor colorWithHexString:@"#009698"];

}

- (void)setupViews{

    [self.view addSubview:self.tableView];
    [self.tableView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.equalTo(self.view);
        make.top.equalTo(self.view).offset(64);
        make.bottom.equalTo(self.view).offset(-49);
    }];
    
}

-(void)bindViewControllerModel{


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
        [_favoriteButton addTarget:self action:@selector(clickFavoriteButton) forControlEvents:(UIControlEventTouchUpInside)];

    }
    return _favoriteButton;
}

-(void)clickFavoriteButton{
    
}

#pragma -mark tableview delegate

-(UITableView *)tableView{

    if (!_tableView) {
        _tableView = [UITableView new];
        [_tableView registerClass:[CHArticleDetailsTableViewCell class] forCellReuseIdentifier:@"articleCell"];
        _tableView.delegate = self;
        _tableView.dataSource = self;
        _tableView.tableFooterView = [UIView new];
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

-(UIStatusBarStyle)preferredStatusBarStyle{

    return UIStatusBarStyleDefault;
}

@end
