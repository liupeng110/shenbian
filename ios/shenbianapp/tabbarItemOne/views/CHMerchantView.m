//
//  CHMerchantView.m
//  shenbianapp
//
//  Created by book on 2017/9/3.
//  Copyright © 2017年 陈坚. All rights reserved.
//

#import "CHMerchantView.h"
#import "CHMerchentTableViewCell.h"

@interface CHMerchantView ()<UITableViewDelegate,UITableViewDataSource>

@property(nonatomic,strong)UITableView *merchentTableView;
@property(nonatomic,strong)NSArray *placeHolderList;
@end


@implementation CHMerchantView

-(instancetype)initWithFrame:(CGRect)frame{

    if (self = [super initWithFrame:frame]) {
        
        [self addSubview:self.merchentTableView];
        [self.merchentTableView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.edges.equalTo(self);
        }];
        @weakify(self);
        [RACObserve(self, merchentList) subscribeNext:^(id x) {
            @strongify(self);
            [self.merchentTableView reloadData];
        }];

        self.placeHolderList = @[@"sy_sj_cover",@"sy_sj_cover"];
    }
    return self;
}

-(UITableView *)merchentTableView{

    if (_merchentTableView == nil) {
        _merchentTableView = [[UITableView alloc]init];
        _merchentTableView.delegate = self;
        _merchentTableView.dataSource = self;
        [_merchentTableView registerClass:[CHMerchentTableViewCell class] forCellReuseIdentifier:@"merchentCell"];
    }
    return _merchentTableView;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{

  return   self.merchentList.count;
}

-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    
    CHMerchentTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"merchentCell" forIndexPath:indexPath];
    cell.selectionStyle = UITableViewCellSelectionStyleNone;
    NSDictionary *dic = [self.merchentList objectAtIndex:indexPath.row];
    CHMerchentModel *model = [[CHMerchentModel alloc] init];
    model.iconUrl = [dic objectForKey:@"icon_url"];
    model.placeHolder = self.placeHolderList[indexPath.row];
    model.rating = [dic objectForKey:@"rating"];
    model.distance = [[dic objectForKey:@"distance"] floatValue];
    model.merchentName = [dic objectForKey:@"merchent_name"];
    model.content = [dic objectForKey:@"content"];
    model.slodOut = [[dic objectForKey:@"sold_out"] integerValue];
    model.tagName = [dic objectForKey:@"tag_name"];
    cell.model = model;
    cell.layer.cornerRadius = 5;
    return cell;
}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{

    return 127;
}

-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
   
    CHMerchentModel *model = [[CHMerchentModel alloc] init];

    self.selectedMerchant(model);

}

@end
