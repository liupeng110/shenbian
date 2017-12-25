//
//  CHServiceAllSerViewController.m
//  shenbianapp
//
//  Created by book on 2017/11/5.
//  Copyright © 2017 helinkeji. All rights reserved.
//

#import "CHServiceAllSerViewController.h"
#import "CHAllServiceTableViewCell.h"
@interface CHServiceAllSerViewController ()<UITableViewDelegate,UITableViewDataSource>
@property(nonatomic,strong)UITableView *tableView;
@property(nonatomic,strong)NSArray *classifyList;
@end

@implementation CHServiceAllSerViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = @"全部分类";
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(void)bindViewControllerModel{

    self.classifyList = @[@"全部分类",@"全部分类",@"全部分类",@"全部分类"];

}

-(void)setupViews{
    [self.view addSubview:self.tableView];
    [self.tableView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.view).offset(64);
        make.left.right.bottom.equalTo(self);
        
    }];

}

-(UITableView *)tableView{

    if (_tableView == nil) {
        _tableView = [UITableView new];
        _tableView.delegate = self;
        _tableView.dataSource = self;
        [_tableView registerClass:[CHAllServiceTableViewCell class] forCellReuseIdentifier:@"classifyCell"];
        _tableView.tableFooterView = [UIView new];
    }
    return _tableView;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return self.classifyList.count;
}
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    
    CHAllServiceTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"classifyCell" forIndexPath:indexPath];
    cell.indexPath = indexPath;

    return cell;
}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{

    return 75;
}

@end
