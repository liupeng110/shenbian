//
//  CHSelectCategoryViewController.m
//  shenbianapp
//
//  Created by book on 2017/11/10.
//  Copyright © 2017年 helinkeji. All rights reserved.
//

#import "CHSelectCategoryViewController.h"

@interface CHSelectCategoryViewController ()<UITableViewDelegate,UITableViewDataSource>

@property(nonatomic,strong)UITableView *tableView;
@property(nonatomic,copy)NSArray *dataArray;

@end

@implementation CHSelectCategoryViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = @"一级类名";
    [self.rightButton setTitle:@"确定" forState:(UIControlStateNormal)];
    self.rightButton.hidden = NO;
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(void)bindViewControllerModel{

    self.dataArray = @[@"类别1",@"类别2",@"类别3"];

}

-(void)setupViews{
    [self.view addSubview:self.tableView];
    [self.tableView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.bottom.equalTo(self.view);
        make.top.equalTo(self.view).offset(64);
    }];

}

-(UITableView *)tableView{

    if (_tableView == nil) {
        _tableView = [UITableView new];
        _tableView.dataSource = self;
        _tableView.delegate = self;
        [_tableView registerClass:[UITableViewCell class] forCellReuseIdentifier:@"categoryCell"];
        _tableView.separatorColor = [UIColor colorWithHexColor:@"#ebebeb"];
        _tableView.tableFooterView = [UIView new];
    }
    return _tableView;

}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
   return self.dataArray.count;
}

-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"categoryCell" forIndexPath:indexPath];
    cell.textLabel.text = self.dataArray[indexPath.row];
    cell.textLabel.font = [UIFont systemFontOfSize:15];
    cell.textLabel.textColor = [UIColor colorWithHexColor:@"#4f5965"];
    
    return cell;
}

-(CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section{
    return 230;
}

-(UIView*)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section{
    
    UIView *contentView = [UIView new];
    contentView.frame = CGRectMake(0, 0, kScreenWidth, 230);
    contentView.backgroundColor = [UIColor whiteColor];
    UIImageView *imageView = [[UIImageView alloc]initWithImage:[UIImage imageNamed:@"sy_sj_cover"]];
    imageView.frame = CGRectMake(0, 0, kScreenWidth, 200);
    [contentView addSubview:imageView];
    
    UILabel *label = [UILabel new];
    label.text = @"选择分类";
    label.textColor = [UIColor colorWithHexColor:@"#2d333a"];
    label.font = [UIFont systemFontOfSize:20];
    [contentView addSubview:label];
    [label mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(contentView).offset(15);
        make.bottom.equalTo(contentView);
        make.width.mas_equalTo(120);
        make.height.mas_equalTo(30);
    }];
    
    return contentView;

}


-(void)clickRightButton{

    [self.navigationController popViewControllerAnimated:YES];
    
}

@end
