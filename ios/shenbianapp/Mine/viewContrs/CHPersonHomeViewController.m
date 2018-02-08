//
//  CHArticleAndServiceListViewController.m
//  shenbianapp
//
//  Created by book on 2017/9/17.
//  Copyright © 2017 . All rights reserved.
//

#import "CHPersonHomeViewController.h"
#import "CHPersonHomeViewModel.h"

#import "CHArticleDetailsViewController.h"
#import "CHArticleListTableViewCell.h"
#import "CHServiceDetailsViewController.h"

@interface CHPersonHomeViewController ()<UITableViewDelegate,UITableViewDataSource>

@property(nonatomic,strong) UIView *topview;
@property(nonatomic,strong) UIButton *myArticleButton;
@property(nonatomic,strong) UIButton *myServiceButton;
@property(nonatomic,strong) UITableView *tableView;
@property(nonatomic,strong) CHPersonHomeViewModel *viewCModel;
@property(nonatomic,strong) UILabel *switcherLabel;
@property(nonatomic,strong) NSMutableArray *modelList;
@end

@implementation CHPersonHomeViewController

@dynamic viewCModel;

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
    
    [self.topview addSubview:self.myServiceButton];
    [self.myServiceButton mas_makeConstraints:^(MASConstraintMaker *make) {
        
        make.top.left.bottom.equalTo(self.topview);
        make.width.mas_equalTo(kScreenWidth/2);
    }];
    
    [self.topview addSubview:self.myArticleButton];
    [self.myArticleButton mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.right.bottom.equalTo(self.topview);
        make.width.mas_equalTo(kScreenWidth/2);
    }];
    [self.topview addSubview:self.switcherLabel];
    [self.switcherLabel mas_makeConstraints:^(MASConstraintMaker *make) {
        make.bottom.equalTo(self.topview);
        make.centerX.equalTo(self.myServiceButton);
        make.height.mas_equalTo(2);
        make.width.mas_equalTo(30);
    }];
    
    [self.view addSubview:self.tableView];
    [self.tableView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.bottom.equalTo(self.view);
        make.top.equalTo(self.topview.mas_bottom).offset(5);
    }];
    
    //导航栏view
    UIView *titleView = [[UIView alloc]initWithFrame:(CGRectMake(0, 0, 150, 44))];
    UIImageView *userImageV = [[UIImageView alloc]initWithImage:[UIImage imageNamed:@"default_headImage"]];
    [userImageV setImageWithURL:[NSURL URLWithString:self.userIcon] placeholder:[UIImage imageNamed:@"default_headImage"]];
    userImageV.layer.cornerRadius = 15;
    userImageV.clipsToBounds = YES;
    [titleView addSubview:userImageV];
    [userImageV mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(titleView);
        make.top.equalTo(titleView).offset(5);
        make.bottom.equalTo(titleView).offset(-5);
        make.width.height.mas_equalTo(30);
    }];
    UILabel *titleLabel = [UILabel new];
    titleLabel.text = self.userName;
    titleLabel.textColor = [UIColor whiteColor];
    [titleView addSubview:titleLabel];
    [titleLabel mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(userImageV.mas_right).offset(5);
        make.top.equalTo(titleView).offset(10);
        make.height.mas_equalTo(20);
        make.right.equalTo(titleView);
    }];
    //    titleView.backgroundColor = [UIColor orangeColor];
    self.navigationItem.titleView = titleView;
    
    UIButton *rightButton = [UIButton buttonWithType:(UIButtonTypeCustom)];
    [rightButton setTitle:@" + 关注  " forState:(UIControlStateNormal)];
    rightButton.layer.cornerRadius = 3;
    rightButton.titleLabel.font = [UIFont systemFontOfSize:15];
    rightButton.backgroundColor = [UIColor whiteColor];
    [rightButton setTitleColor:[UIColor colorWithHexString:@"009698"] forState:(UIControlStateNormal)];
    [rightButton addTarget:self action:@selector(clickFocusButton:) forControlEvents:(UIControlEventTouchDown)];
    rightButton.titleLabel.font = [UIFont systemFontOfSize:15];
    self.navigationItem.rightBarButtonItem = [[UIBarButtonItem alloc]initWithCustomView:rightButton];
}

-(void)clickFocusButton:(UIButton*)button{
    NSString *message = @"";
    if (button.tag==0) {
        button.tag = 1;
        [button setTitle:@"已关注" forState:(UIControlStateNormal)];
        message = @"关注成功";
    } else {
        button.tag = 0;
        [button setTitle:@" + 关注  " forState:(UIControlStateNormal)];
        message = @"已取消关注";
    }
    
    UIAlertView *alert = [[UIAlertView alloc]initWithTitle:@"温馨提示" message:message delegate:nil
                                         cancelButtonTitle:@"知晓" otherButtonTitles:nil];
    [alert show];
    
    NSString *concernStatus = [NSString stringWithFormat:@"%ld",button.tag];
    NSString *token  = [[NSUserDefaults standardUserDefaults] objectForKey:@"token"];
    RACSignal *singal = [self.viewCModel.focusCommand execute:@{@"token":token,@"concernStatus":concernStatus}];
    
    [singal subscribeNext:^(id x) {
      
    } error:^(NSError *error) {
        NSLog(@"error:%@",error);
    }];
}

-(void)bindViewControllerModel{
    [super bindViewControllerModel];
    self.viewCModel = [CHPersonHomeViewModel new];
    NSString *token = [[NSUserDefaults standardUserDefaults] objectForKey:@"token"];
    RACSignal *signal = [self.viewCModel.loadPagedata execute:@{@"token":token,@"userId":self.userId}];
    @weakify(self);
    [signal subscribeNext:^(id x) {
        @strongify(self);
        self.modelList = [[x objectForKey:@"data"] objectForKey:@"services"];
        [self.tableView reloadData];
    }];
}

-(void)viewWillAppear:(BOOL)animated{
    self.navigationController.navigationBarHidden = NO;
}

-(void)viewWillDisappear:(BOOL)animated{
    [super viewWillDisappear:animated];
    self.navigationController.navigationBarHidden = YES;
    
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
        [_myArticleButton setTitleColor:[UIColor colorWithHexString:@"#4f5965"] forState:(UIControlStateNormal)];
        _myArticleButton.titleLabel.font = [UIFont systemFontOfSize:15];
//        [_myArticleButton addTarget:self action:@selector(switchArticleAndService:) forControlEvents:(UIControlEventTouchUpInside)];
        
    }
    return _myArticleButton;
}

-(UIButton *)myServiceButton{
    
    if (_myServiceButton == nil) {
        _myServiceButton = [UIButton buttonWithType:(UIButtonTypeCustom)];
        _myServiceButton.titleLabel.font = [UIFont systemFontOfSize:15];
        [_myServiceButton setTitle:@"服务" forState:(UIControlStateNormal)];
        [_myServiceButton setTitleColor:[UIColor colorWithHexString:@"#009698"] forState:(UIControlStateNormal)];
        //        [_myServiceButton addTarget:self action:@selector(switchArticleAndService:) forControlEvents:(UIControlEventTouchUpInside)];
    }
    
    return _myServiceButton;
}

-(UILabel *)switcherLabel{
    if (!_switcherLabel) {
        _switcherLabel = [UILabel new];
        _switcherLabel.backgroundColor = [UIColor colorWithHexString:@"#009698"];
    }
    return _switcherLabel;
}

- (void)switchArticleAndService:(UIButton*)button{
    
    if (button == self.myServiceButton) {
        [_myServiceButton setTitleColor:[UIColor colorWithHexString:@"#009698"] forState:(UIControlStateNormal)];
        [_myArticleButton setTitleColor:[UIColor colorWithHexString:@"#4f5965"] forState:(UIControlStateNormal)];
        [self.switcherLabel mas_remakeConstraints:^(MASConstraintMaker *make) {
            make.centerX.equalTo(self.myServiceButton);
            make.bottom.equalTo(self.topview);
            make.height.mas_equalTo(2);
            make.width.mas_equalTo(30);
        }];
        self.viewCModel.provideTye = ProvideTypeService;
        self.title = @"我的服务";
    } else {
        [_myArticleButton setTitleColor:[UIColor colorWithHexString:@"#009698"] forState:(UIControlStateNormal)];
        [_myServiceButton setTitleColor:[UIColor colorWithHexString:@"#4f5965"] forState:(UIControlStateNormal)];
        [self.switcherLabel mas_remakeConstraints:^(MASConstraintMaker *make) {
            make.bottom.equalTo(self.topview);
            make.centerX.equalTo(self.myArticleButton);
            make.height.mas_equalTo(2);
            make.width.mas_equalTo(30);
            
        }];
        self.viewCModel.provideTye = ProvideTypeArticle;
        self.title = @"我的文章";
    }
    [UIView animateWithDuration:0.25 animations:^{
        
        [self.topview layoutIfNeeded];
        
    }];
}



#pragma -mark  tableview

-(UITableView *)tableView{
    
    if (_tableView == nil) {
        _tableView = [[UITableView alloc]init];
        _tableView.delegate = self;
        _tableView.dataSource = self;
        [_tableView registerClass:[CHArticleListTableViewCell class] forCellReuseIdentifier:@"articleListCell"];
        _tableView.tableFooterView = [UIView new];
    }
    return _tableView;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    
    if (self.viewCModel.provideTye == ProvideTypeService) {
        return self.modelList.count;
        
    } else {
        return 0;
    }
    
}

-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    CHArticleListTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"articleListCell" forIndexPath:indexPath];
    cell.selectionStyle = UITableViewCellSelectionStyleNone;
    cell.dataDic = self.modelList[indexPath.row];
    cell.indexPath = indexPath;

    return cell;
}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    
    return 120;
}

-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    
    if (self.viewCModel.provideTye == ProvideTypeArticle) {
        
        //        CHArticleDetailsViewController *articleDetailVC = [CHArticleDetailsViewController new];
        //        [self.navigationController pushViewController:articleDetailVC animated:YES];
        
    } else {
        
        CHServiceDetailsViewController *serviceDetail = [CHServiceDetailsViewController new];
        serviceDetail.serviceId = [self.modelList[indexPath.row] objectForKey:@"id"];
        [self.navigationController pushViewController:serviceDetail animated:YES];
        
    }
    
}


@end
