//
//  RootFocusViewController.m
//  shenbianapp
//
//  Created by 杨绍智 on 17/7/12.
//  Copyright © 2017年 杨绍智. All rights reserved.
//

#import "RootFocusViewController.h"
#import "FocusTableViewCell.h"
#import "FocusRecommentTableViewCell.h"
@interface RootFocusViewController ()<UITableViewDelegate,UITableViewDataSource>
@property (nonatomic,strong)UITableView * focusTableView;
@property (nonatomic,strong)NSMutableArray * itemArray;
@end

@implementation RootFocusViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.navBarView.mhBaseTitleLabel.text = @"身边号";
    self.focusTableView = [[UITableView alloc]initWithFrame:CGRectMake(0, 64, kScreenWidth, kScreenHeight-64-49) style:UITableViewStylePlain];
    self.focusTableView.delegate = self;
    self.focusTableView.dataSource = self;
    self.focusTableView.backgroundColor = [UIColor colorWithHexColor:@"#f6f7f9"];
    self.focusTableView.separatorColor = [UIColor clearColor];
    self.focusTableView.showsVerticalScrollIndicator = NO;
    [self.view addSubview:self.focusTableView];
    [self.focusTableView registerClass:[FocusTableViewCell class] forCellReuseIdentifier:@"FocusTableViewCell"];
    [self.focusTableView registerClass:[FocusRecommentTableViewCell class] forCellReuseIdentifier:@"FocusRecommentTableViewCell"];
}
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return 10;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    if (indexPath.row==2) {
       return 215.0;
    }else{
       return 210.0;
    }
}

- (UITableViewCell*)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    if (indexPath.row==2) {
        FocusRecommentTableViewCell * cell = [tableView dequeueReusableCellWithIdentifier:@"FocusRecommentTableViewCell" forIndexPath:indexPath];
        cell.selectionStyle = UITableViewCellSelectionStyleNone;
        return cell;
    }else{
        FocusTableViewCell * cell = [tableView dequeueReusableCellWithIdentifier:@"FocusTableViewCell" forIndexPath:indexPath];
        cell.selectionStyle = UITableViewCellSelectionStyleNone;
        return cell;
    }
   
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


@end
